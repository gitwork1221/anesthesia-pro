package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AnesthesiaDatabase
import com.example.data.local.BookmarkEntity
import com.example.data.local.UserNoteEntity
import com.example.data.model.*
import com.example.data.repository.AnesthesiaRepository
import com.example.data.repository.CalculatorsEngine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface NavigationDestination {
    data object Dashboard : NavigationDestination
    data object Curriculum : NavigationDestination
    data object Pharmacology : NavigationDestination
    data object Calculators : NavigationDestination
    data object SimAndQuiz : NavigationDestination
    data object AnatomyViewer : NavigationDestination
    data object BookmarksAndNotes : NavigationDestination
    data object Settings : NavigationDestination
    data class LessonDetail(val lessonId: String) : NavigationDestination
    data class DrugDetail(val drugId: String) : NavigationDestination
    data class SurgicalGuideDetail(val guideId: String) : NavigationDestination
    data class EquipmentDetail(val equipId: String) : NavigationDestination
    data class CaseSimDetail(val caseId: String) : NavigationDestination
}

data class SearchResultItem(
    val id: String,
    val title: String,
    val categoryName: String,
    val type: String, // "Lesson", "Drug", "Surgical Guide", "Equipment", "Calculator"
    val destination: NavigationDestination
)

data class CaseSimState(
    val currentStageIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val hasAnsweredCurrentStage: Boolean = false,
    val stagesPassedCount: Int = 0,
    val isCompleted: Boolean = false
)

data class QuizState(
    val currentQuestionIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val isAnswerSubmitted: Boolean = false,
    val score: Int = 0,
    val isFinished: Boolean = false,
    val userAnswers: Map<Int, Int> = emptyMap() // questionIndex -> selectedIndex
)

class AnesthesiaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AnesthesiaRepository

    init {
        val database = AnesthesiaDatabase.getDatabase(application)
        repository = AnesthesiaRepository(database.anesthesiaDao())
    }

    // Navigation state stack
    private val _navStack = MutableStateFlow<List<NavigationDestination>>(listOf(NavigationDestination.Dashboard))
    val currentDestination: StateFlow<NavigationDestination> = MutableStateFlow<NavigationDestination>(NavigationDestination.Dashboard).apply {
        viewModelScope.launch {
            _navStack.collect { stack ->
                value = stack.lastOrNull() ?: NavigationDestination.Dashboard
            }
        }
    }

    // Ethiopian BSc Curriculum & Student Profile State
    val pathway = MutableStateFlow(LearningPathway.ETHIOPIAN_BSC_ANESTHESIA)
    val studyLevel = MutableStateFlow(StudyLevel.YEAR_2)
    val selectedAcademicYear = MutableStateFlow(AcademicYear.YEAR_2)
    val studyStreakDays = MutableStateFlow(0) // Real stored streak, defaults to 0 for new learner
    val dailyGoalMinutes = MutableStateFlow(25)
    val completedLessonIds = MutableStateFlow<Set<String>>(emptySet())
    val lastOpenedLessonId = MutableStateFlow<String?>(null)
    val textScale = MutableStateFlow(1.0f) // Reading font scale (0.85f, 1.0f, 1.2f, 1.35f)

    // Advanced Persistent Settings State
    val themeMode = MutableStateFlow(AppThemeMode.DARK)
    val textScaleOption = MutableStateFlow(TextScaleOption.STANDARD)
    val readingLineSpacing = MutableStateFlow(ReadingLineSpacing.STANDARD)
    val highContrastMode = MutableStateFlow(false)
    val explanationLevel = MutableStateFlow(ExplanationLevel.INTERMEDIATE)
    val mcqDifficulty = MutableStateFlow(McqDifficulty.MIXED)
    val defaultQuestionCount = MutableStateFlow(10)
    val showExplanationsImmediately = MutableStateFlow(true)
    val showLearningObjectives = MutableStateFlow(true)
    val studyReminderEnabled = MutableStateFlow(false)
    val studyReminderTime = MutableStateFlow("20:00")
    val studyReminderDays = MutableStateFlow(setOf("MON", "TUE", "WED", "THU", "FRI"))

    // Bookmarks and notes from Room
    val bookmarks: StateFlow<List<BookmarkEntity>> = repository.bookmarks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allNotes: StateFlow<List<UserNoteEntity>> = repository.allNotes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Search state
    val searchQuery = MutableStateFlow("")
    val searchResults = MutableStateFlow<List<SearchResultItem>>(emptyList())

    // Simulator & Quiz state
    val caseSimState = MutableStateFlow(CaseSimState())
    val quizState = MutableStateFlow(QuizState())

    // Active Category filter in Curriculum
    val selectedCurriculumCategory = MutableStateFlow<CurriculumCategory?>(null)

    // Active Drug Filter
    val selectedDrugCategoryFilter = MutableStateFlow<com.example.data.model.DrugCategory?>(null)
    val selectedDrugClassFilter = MutableStateFlow<String?>(null)

    init {
        loadPreferences()
    }

    private fun loadPreferences() {
        viewModelScope.launch {
            val savedPathway = repository.getPreference(AnesthesiaRepository.PREF_PATHWAY)
            if (savedPathway != null) {
                runCatching { pathway.value = LearningPathway.valueOf(savedPathway) }
            }
            val savedLevel = repository.getPreference(AnesthesiaRepository.PREF_STUDY_LEVEL)
            if (savedLevel != null) {
                runCatching {
                    val lvl = StudyLevel.valueOf(savedLevel)
                    studyLevel.value = lvl
                    selectedAcademicYear.value = lvl.academicYear
                }
            }
            val savedStreak = repository.getPreference(AnesthesiaRepository.PREF_STUDY_STREAK)
            if (savedStreak != null) {
                studyStreakDays.value = savedStreak.toIntOrNull() ?: 0
            }
            val savedCompleted = repository.getPreference(AnesthesiaRepository.PREF_COMPLETED_LESSONS)
            if (!savedCompleted.isNullOrBlank()) {
                completedLessonIds.value = savedCompleted.split(",").filter { it.isNotBlank() }.toSet()
            }
            val savedLastOpened = repository.getPreference(AnesthesiaRepository.PREF_LAST_OPENED_LESSON)
            if (!savedLastOpened.isNullOrBlank()) {
                lastOpenedLessonId.value = savedLastOpened
            }

            // Load Settings
            val savedTheme = repository.getPreference(AnesthesiaRepository.PREF_THEME_MODE)
            if (savedTheme != null) runCatching { themeMode.value = AppThemeMode.valueOf(savedTheme) }

            val savedScale = repository.getPreference(AnesthesiaRepository.PREF_TEXT_SCALE)
            if (savedScale != null) runCatching {
                val scale = savedScale.toFloatOrNull() ?: 1.0f
                textScale.value = scale
                textScaleOption.value = TextScaleOption.fromScale(scale)
            }

            val savedSpacing = repository.getPreference(AnesthesiaRepository.PREF_LINE_SPACING)
            if (savedSpacing != null) runCatching { readingLineSpacing.value = ReadingLineSpacing.valueOf(savedSpacing) }

            val savedContrast = repository.getPreference(AnesthesiaRepository.PREF_HIGH_CONTRAST)
            if (savedContrast != null) highContrastMode.value = savedContrast.toBoolean()

            val savedExpl = repository.getPreference(AnesthesiaRepository.PREF_EXPLANATION_LEVEL)
            if (savedExpl != null) runCatching { explanationLevel.value = ExplanationLevel.valueOf(savedExpl) }

            val savedMcq = repository.getPreference(AnesthesiaRepository.PREF_MCQ_DIFFICULTY)
            if (savedMcq != null) runCatching { mcqDifficulty.value = McqDifficulty.valueOf(savedMcq) }

            val savedCount = repository.getPreference(AnesthesiaRepository.PREF_DEFAULT_QUESTION_COUNT)
            if (savedCount != null) defaultQuestionCount.value = savedCount.toIntOrNull() ?: 10

            val savedImmediate = repository.getPreference(AnesthesiaRepository.PREF_SHOW_EXPLANATIONS_IMMEDIATELY)
            if (savedImmediate != null) showExplanationsImmediately.value = savedImmediate.toBoolean()

            val savedObjectives = repository.getPreference(AnesthesiaRepository.PREF_SHOW_LEARNING_OBJECTIVES)
            if (savedObjectives != null) showLearningObjectives.value = savedObjectives.toBoolean()

            val savedReminder = repository.getPreference(AnesthesiaRepository.PREF_STUDY_REMINDER_ENABLED)
            if (savedReminder != null) studyReminderEnabled.value = savedReminder.toBoolean()

            val savedTime = repository.getPreference(AnesthesiaRepository.PREF_STUDY_REMINDER_TIME)
            if (savedTime != null) studyReminderTime.value = savedTime

            val savedDays = repository.getPreference(AnesthesiaRepository.PREF_STUDY_REMINDER_DAYS)
            if (savedDays != null) studyReminderDays.value = savedDays.split(",").filter { it.isNotBlank() }.toSet()
        }
    }

    // Navigation actions
    fun navigateTo(dest: NavigationDestination) {
        _navStack.update { current ->
            if (current.lastOrNull() == dest) current else current + dest
        }
    }

    fun popBack(): Boolean {
        if (_navStack.value.size > 1) {
            _navStack.update { it.dropLast(1) }
            return true
        }
        return false
    }

    fun selectBottomTab(dest: NavigationDestination) {
        _navStack.value = listOf(dest)
    }

    // Pathway updates
    fun updatePathway(newPathway: LearningPathway) {
        pathway.value = newPathway
        viewModelScope.launch {
            repository.savePreference(AnesthesiaRepository.PREF_PATHWAY, newPathway.name)
        }
    }

    fun updateStudyLevel(newLevel: StudyLevel) {
        studyLevel.value = newLevel
        selectedAcademicYear.value = newLevel.academicYear
        viewModelScope.launch {
            repository.savePreference(AnesthesiaRepository.PREF_STUDY_LEVEL, newLevel.name)
        }
    }

    fun recordLessonOpened(lessonId: String) {
        lastOpenedLessonId.value = lessonId
        viewModelScope.launch {
            repository.savePreference(AnesthesiaRepository.PREF_LAST_OPENED_LESSON, lessonId)
        }
    }

    // Lesson completion
    fun toggleLessonCompleted(lessonId: String) {
        val current = completedLessonIds.value
        val updated = if (current.contains(lessonId)) current - lessonId else current + lessonId
        completedLessonIds.value = updated
        viewModelScope.launch {
            repository.savePreference(AnesthesiaRepository.PREF_COMPLETED_LESSONS, updated.joinToString(","))
        }
    }

    // Bookmarks
    fun isItemBookmarked(id: String): Boolean {
        return bookmarks.value.any { it.id == id }
    }

    fun toggleBookmark(id: String, type: String, title: String, subtitle: String) {
        viewModelScope.launch {
            val currently = isItemBookmarked(id)
            repository.toggleBookmark(id, type, title, subtitle, currently)
        }
    }

    // Notes
    fun addNote(targetId: String, targetType: String, targetTitle: String, content: String) {
        viewModelScope.launch {
            repository.addNote(targetId, targetType, targetTitle, content)
        }
    }

    fun deleteNote(noteId: Int) {
        viewModelScope.launch {
            repository.deleteNote(noteId)
        }
    }

    // Font size scaling
    fun cycleTextScale() {
        textScale.value = when (textScale.value) {
            1.0f -> 1.18f
            1.18f -> 1.35f
            else -> 1.0f
        }
    }

    // Global Search
    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
        searchResults.value = SearchHelper.performSearch(repository, query)
    }

    // Case Simulator operations
    fun startCaseSimulation(caseId: String) {
        caseSimState.value = CaseSimState(currentStageIndex = 0)
        navigateTo(NavigationDestination.CaseSimDetail(caseId))
    }

    fun submitCaseStageOption(optionIndex: Int, isCorrect: Boolean) {
        caseSimState.update { current ->
            current.copy(
                selectedOptionIndex = optionIndex,
                hasAnsweredCurrentStage = true,
                stagesPassedCount = if (isCorrect) current.stagesPassedCount + 1 else current.stagesPassedCount
            )
        }
    }

    fun proceedToNextCaseStage(totalStages: Int) {
        caseSimState.update { current ->
            if (current.currentStageIndex + 1 >= totalStages) {
                current.copy(isCompleted = true)
            } else {
                current.copy(
                    currentStageIndex = current.currentStageIndex + 1,
                    selectedOptionIndex = null,
                    hasAnsweredCurrentStage = false
                )
            }
        }
    }

    fun restartCaseSimulation() {
        caseSimState.value = CaseSimState(currentStageIndex = 0)
    }

    // Quiz operations
    fun startQuiz() {
        quizState.value = QuizState()
        navigateTo(NavigationDestination.SimAndQuiz)
    }

    fun submitQuizAnswer(selectedOptionIndex: Int, correctIndex: Int) {
        val current = quizState.value
        val isCorrect = selectedOptionIndex == correctIndex
        quizState.value = current.copy(
            selectedOptionIndex = selectedOptionIndex,
            isAnswerSubmitted = true,
            score = if (isCorrect) current.score + 1 else current.score,
            userAnswers = current.userAnswers + (current.currentQuestionIndex to selectedOptionIndex)
        )
    }

    fun nextQuizQuestion(totalQuestions: Int) {
        val current = quizState.value
        if (current.currentQuestionIndex + 1 >= totalQuestions) {
            quizState.value = current.copy(isFinished = true)
        } else {
            quizState.value = current.copy(
                currentQuestionIndex = current.currentQuestionIndex + 1,
                selectedOptionIndex = null,
                isAnswerSubmitted = false
            )
        }
    }

    fun resetQuiz() {
        quizState.value = QuizState()
    }

    // Content accessors for UI
    fun getLessons() = repository.getAllLessons()
    fun getLesson(id: String) = repository.getLessonById(id)
    fun getAllModules() = repository.getAllModules()
    fun getModulesByYear(year: AcademicYear) = repository.getModulesByYear(year)
    fun getLessonsByYear(year: AcademicYear) = repository.getLessonsByYear(year)
    fun getLessonsByModule(moduleId: String) = repository.getLessonsByModule(moduleId)
    fun getLastOpenedLesson(): Lesson? {
        val id = lastOpenedLessonId.value ?: return null
        return repository.getLessonById(id)
    }
    fun getOverallProgressFraction(): Float {
        val all = repository.getAllLessons()
        if (all.isEmpty()) return 0f
        val completed = completedLessonIds.value
        val count = all.count { completed.contains(it.id) }
        return count.toFloat() / all.size.toFloat()
    }
    fun getModuleProgressFraction(moduleId: String): Float {
        val moduleLessons = repository.getLessonsByModule(moduleId)
        if (moduleLessons.isEmpty()) return 0f
        val completed = completedLessonIds.value
        val count = moduleLessons.count { completed.contains(it.id) }
        return count.toFloat() / moduleLessons.size.toFloat()
    }
    fun getModuleCompletedLessonCount(moduleId: String): Int =
        repository.getLessonsByModule(moduleId).count { completedLessonIds.value.contains(it.id) }

    fun getDrugs(): List<Drug> = repository.getAllDrugs()
    fun getDrug(id: String): Drug? = repository.getDrugById(id)
    fun getSurgicalGuides(): List<com.example.data.model.SurgicalGuide> = repository.getAllSurgicalGuides()
    fun getSurgicalGuide(id: String): com.example.data.model.SurgicalGuide? = repository.getSurgicalGuideById(id)
    fun getEquipmentModules(): List<com.example.data.model.EquipmentModule> = repository.getAllEquipmentModules()
    fun getEquipment(id: String): com.example.data.model.EquipmentModule? = repository.getEquipmentById(id)
    fun getClinicalCases(): List<com.example.data.model.ClinicalCase> = repository.getAllClinicalCases()
    fun getClinicalCase(id: String): com.example.data.model.ClinicalCase? = repository.getClinicalCaseById(id)
    fun getQuizQuestions(): List<com.example.data.model.QuizQuestion> = repository.getAllQuizQuestions()

    // Advanced Settings Mutators
    fun setThemeMode(mode: AppThemeMode) {
        themeMode.value = mode
        viewModelScope.launch { repository.savePreference(AnesthesiaRepository.PREF_THEME_MODE, mode.name) }
    }

    fun setTextScaleOption(option: TextScaleOption) {
        textScaleOption.value = option
        textScale.value = option.scale
        viewModelScope.launch { repository.savePreference(AnesthesiaRepository.PREF_TEXT_SCALE, option.scale.toString()) }
    }

    fun setReadingLineSpacing(spacing: ReadingLineSpacing) {
        readingLineSpacing.value = spacing
        viewModelScope.launch { repository.savePreference(AnesthesiaRepository.PREF_LINE_SPACING, spacing.name) }
    }

    fun setHighContrastMode(enabled: Boolean) {
        highContrastMode.value = enabled
        viewModelScope.launch { repository.savePreference(AnesthesiaRepository.PREF_HIGH_CONTRAST, enabled.toString()) }
    }
    fun setExplanationLevel(level: ExplanationLevel) {
        explanationLevel.value = level
        viewModelScope.launch { repository.savePreference(AnesthesiaRepository.PREF_EXPLANATION_LEVEL, level.name) }
    }
    fun setMcqDifficulty(difficulty: McqDifficulty) {
        mcqDifficulty.value = difficulty
        viewModelScope.launch { repository.savePreference(AnesthesiaRepository.PREF_MCQ_DIFFICULTY, difficulty.name) }
    }
    fun setDefaultQuestionCount(count: Int) {
        defaultQuestionCount.value = count
        viewModelScope.launch { repository.savePreference(AnesthesiaRepository.PREF_DEFAULT_QUESTION_COUNT, count.toString()) }
    }
    fun setShowExplanationsImmediately(show: Boolean) {
        showExplanationsImmediately.value = show
        viewModelScope.launch { repository.savePreference(AnesthesiaRepository.PREF_SHOW_EXPLANATIONS_IMMEDIATELY, show.toString()) }
    }
    fun setShowLearningObjectives(show: Boolean) {
        showLearningObjectives.value = show
        viewModelScope.launch { repository.savePreference(AnesthesiaRepository.PREF_SHOW_LEARNING_OBJECTIVES, show.toString()) }
    }

    fun setStudyReminder(enabled: Boolean, time: String, days: Set<String>) {
        studyReminderEnabled.value = enabled
        studyReminderTime.value = time
        studyReminderDays.value = days
        viewModelScope.launch {
            repository.savePreference(AnesthesiaRepository.PREF_STUDY_REMINDER_ENABLED, enabled.toString())
            repository.savePreference(AnesthesiaRepository.PREF_STUDY_REMINDER_TIME, time)
            repository.savePreference(AnesthesiaRepository.PREF_STUDY_REMINDER_DAYS, days.joinToString(","))
        }
    }

    fun resetPreferencesToDefault() {
        setThemeMode(AppThemeMode.DARK)
        setTextScaleOption(TextScaleOption.STANDARD)
        setReadingLineSpacing(ReadingLineSpacing.STANDARD)
        setHighContrastMode(false)
        setExplanationLevel(ExplanationLevel.INTERMEDIATE)
        setMcqDifficulty(McqDifficulty.MIXED)
        setDefaultQuestionCount(10)
        setShowExplanationsImmediately(true)
        setShowLearningObjectives(true)
        setStudyReminder(false, "20:00", setOf("MON", "TUE", "WED", "THU", "FRI"))
    }

    fun resetLearnerProgress() {
        completedLessonIds.value = emptySet()
        studyStreakDays.value = 0
        lastOpenedLessonId.value = null
        viewModelScope.launch {
            repository.savePreference(AnesthesiaRepository.PREF_COMPLETED_LESSONS, "")
            repository.savePreference(AnesthesiaRepository.PREF_STUDY_STREAK, "0")
            repository.savePreference(AnesthesiaRepository.PREF_LAST_OPENED_LESSON, "")
        }
    }

    fun clearAllLearnerNotes() = viewModelScope.launch { repository.clearAllNotes() }
    fun clearAllLearnerBookmarks() = viewModelScope.launch { repository.clearAllBookmarks() }
    fun clearCache(): Long = SettingsHelper.clearCache(getApplication())

    fun getStorageUsageInfo(): StorageUsageInfo = SettingsHelper.calculateStorageUsage(
        context = getApplication(),
        lessonsCount = repository.getAllLessons().size,
        drugsCount = repository.getAllDrugs().size,
        guidesCount = repository.getAllSurgicalGuides().size,
        anatomyCount = com.example.data.repository.anatomy.AnatomyRepository.allTopics.size
    )

    fun exportLearnerData(): String = SettingsHelper.generateProgressExportSummary(
        pathway = pathway.value.displayName,
        studyLevel = studyLevel.value.displayName,
        academicYear = selectedAcademicYear.value.romanNumeral,
        completedLessonIds = completedLessonIds.value,
        totalLessonsCount = repository.getAllLessons().size,
        notesCount = allNotes.value.size,
        bookmarksCount = bookmarks.value.size,
        streakDays = studyStreakDays.value
    )
}
