package com.example.data.repository

import com.example.data.local.AnesthesiaDao
import com.example.data.local.BookmarkEntity
import com.example.data.local.UserNoteEntity
import com.example.data.local.UserPreferenceEntity
import com.example.data.model.ClinicalCase
import com.example.data.model.CurriculumCategory
import com.example.data.model.Drug
import com.example.data.model.EquipmentModule
import com.example.data.model.LearningPathway
import com.example.data.model.Lesson
import com.example.data.model.QuizQuestion
import com.example.data.model.StudyLevel
import com.example.data.model.SurgicalGuide
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AnesthesiaRepository(private val dao: AnesthesiaDao) {

    // Educational content access
    fun getAllLessons(): List<Lesson> = CurriculumData.lessons
    fun getLessonsByCategory(category: CurriculumCategory): List<Lesson> =
        CurriculumData.lessons.filter { it.category == category }

    fun getLessonById(id: String): Lesson? =
        CurriculumData.lessons.firstOrNull { it.id == id }

    fun getAllModules(): List<com.example.data.model.CurriculumModule> = CurriculumData.modules
    fun getModulesByYear(year: com.example.data.model.AcademicYear): List<com.example.data.model.CurriculumModule> =
        CurriculumData.getModulesByYear(year)
    fun getLessonsByModule(moduleId: String): List<Lesson> =
        CurriculumData.getLessonsByModule(moduleId)
    fun getLessonsByYear(year: com.example.data.model.AcademicYear): List<Lesson> =
        CurriculumData.getLessonsByYear(year)

    fun getAllDrugs(): List<Drug> = PharmacologyData.drugs
    fun getDrugById(id: String): Drug? = PharmacologyData.drugs.firstOrNull { it.id == id }
    fun getDrugsByCategory(category: com.example.data.model.DrugCategory): List<Drug> =
        PharmacologyData.getDrugsByCategory(category)

    fun getAllSurgicalGuides(): List<SurgicalGuide> = SurgicalAndEquipmentData.surgicalGuides
    fun getSurgicalGuideById(id: String): SurgicalGuide? =
        SurgicalAndEquipmentData.surgicalGuides.firstOrNull { it.id == id }

    fun getAllEquipmentModules(): List<EquipmentModule> = SurgicalAndEquipmentData.equipmentModules
    fun getEquipmentById(id: String): EquipmentModule? =
        SurgicalAndEquipmentData.equipmentModules.firstOrNull { it.id == id }

    fun getAllClinicalCases(): List<ClinicalCase> = SimulationAndQuizData.clinicalCases
    fun getClinicalCaseById(id: String): ClinicalCase? =
        SimulationAndQuizData.clinicalCases.firstOrNull { it.id == id }

    fun getAllQuizQuestions(): List<QuizQuestion> = SimulationAndQuizData.quizQuestions

    // Bookmarks (Reactive via Room)
    val bookmarks: Flow<List<BookmarkEntity>> = dao.getAllBookmarks()

    fun isBookmarked(id: String): Flow<Boolean> = dao.isBookmarked(id)

    suspend fun toggleBookmark(id: String, type: String, title: String, subtitle: String, currentlyBookmarked: Boolean) {
        if (currentlyBookmarked) {
            dao.deleteBookmark(id)
        } else {
            dao.insertBookmark(
                BookmarkEntity(
                    id = id,
                    type = type,
                    title = title,
                    subtitle = subtitle
                )
            )
        }
    }

    // Notes (Reactive via Room)
    fun getNotesForTarget(targetId: String): Flow<List<UserNoteEntity>> = dao.getNotesForTarget(targetId)
    val allNotes: Flow<List<UserNoteEntity>> = dao.getAllNotes()

    suspend fun addNote(targetId: String, targetType: String, targetTitle: String, content: String) {
        dao.insertNote(
            UserNoteEntity(
                targetId = targetId,
                targetType = targetType,
                targetTitle = targetTitle,
                content = content
            )
        )
    }

    suspend fun deleteNote(noteId: Int) {
        dao.deleteNote(noteId)
    }

    // User Preferences & Progress
    val userPreferences: Flow<Map<String, String>> = dao.getAllPreferences().map { list ->
        list.associate { it.prefKey to it.prefValue }
    }

    suspend fun getPreference(key: String): String? {
        return dao.getPreference(key)
    }

    suspend fun savePreference(key: String, value: String) {
        dao.setPreference(UserPreferenceEntity(prefKey = key, prefValue = value))
    }

    suspend fun deletePreference(key: String) {
        dao.deletePreference(key)
    }

    suspend fun clearAllBookmarks() {
        dao.clearAllBookmarks()
    }

    suspend fun clearAllNotes() {
        dao.clearAllNotes()
    }

    // Helper keys for local state & settings
    companion object {
        const val PREF_PATHWAY = "user_pathway"
        const val PREF_STUDY_LEVEL = "user_study_level"
        const val PREF_STUDY_STREAK = "user_study_streak"
        const val PREF_LAST_STUDY_DATE = "user_last_study_date"
        const val PREF_DAILY_GOAL = "user_daily_goal_minutes"
        const val PREF_COMPLETED_LESSONS = "user_completed_lessons" // comma separated IDs
        const val PREF_LAST_OPENED_LESSON = "user_last_opened_lesson_id"
        const val PREF_QUIZ_HIGH_SCORE = "user_quiz_high_score"

        // Advanced Settings Keys
        const val PREF_THEME_MODE = "setting_theme_mode" // SYSTEM, DARK, LIGHT
        const val PREF_TEXT_SCALE = "setting_text_scale" // Float (0.85, 1.0, 1.2, 1.35)
        const val PREF_LINE_SPACING = "setting_line_spacing" // COMPACT, STANDARD, RELAXED
        const val PREF_HIGH_CONTRAST = "setting_high_contrast" // Boolean
        const val PREF_EXPLANATION_LEVEL = "setting_explanation_level" // BEGINNER, INTERMEDIATE, ADVANCED
        const val PREF_MCQ_DIFFICULTY = "setting_mcq_difficulty" // EASY, MODERATE, HARD, MIXED
        const val PREF_DEFAULT_QUESTION_COUNT = "setting_default_question_count" // Int (5, 10, 15, 20)
        const val PREF_SHOW_EXPLANATIONS_IMMEDIATELY = "setting_show_explanations_immediately" // Boolean
        const val PREF_SHOW_LEARNING_OBJECTIVES = "setting_show_learning_objectives" // Boolean
        const val PREF_STUDY_REMINDER_ENABLED = "setting_study_reminder_enabled" // Boolean
        const val PREF_STUDY_REMINDER_TIME = "setting_study_reminder_time" // String "20:00"
        const val PREF_STUDY_REMINDER_DAYS = "setting_study_reminder_days" // Comma-separated days
    }
}
