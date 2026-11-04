package com.example

import com.example.data.model.*
import com.example.data.repository.AnesthesiaRepository
import com.example.ui.viewmodel.SettingsHelper
import org.junit.Assert.*
import org.junit.Test
import java.io.File

class SettingsUnitTest {

    @Test
    fun testAppThemeModeOptions() {
        val modes = AppThemeMode.entries
        assertEquals(3, modes.size)
        assertTrue(modes.contains(AppThemeMode.SYSTEM))
        assertTrue(modes.contains(AppThemeMode.LIGHT))
        assertTrue(modes.contains(AppThemeMode.DARK))

        assertEquals("Clinical Dark", AppThemeMode.DARK.displayName)
        assertEquals("Medical Light", AppThemeMode.LIGHT.displayName)
        assertEquals("System Default", AppThemeMode.SYSTEM.displayName)

        // Verify valueOf roundtrip
        for (mode in modes) {
            assertEquals(mode, AppThemeMode.valueOf(mode.name))
        }
    }

    @Test
    fun testTextScaleOptionsAndFactors() {
        val scales = TextScaleOption.entries
        assertEquals(4, scales.size)

        assertEquals(0.85f, TextScaleOption.SMALL.scale, 0.01f)
        assertEquals(1.0f, TextScaleOption.STANDARD.scale, 0.01f)
        assertEquals(1.2f, TextScaleOption.LARGE.scale, 0.01f)
        assertEquals(1.35f, TextScaleOption.EXTRA_LARGE.scale, 0.01f)

        // Verify ordering
        assertTrue(TextScaleOption.SMALL.scale < TextScaleOption.STANDARD.scale)
        assertTrue(TextScaleOption.STANDARD.scale < TextScaleOption.LARGE.scale)
        assertTrue(TextScaleOption.LARGE.scale < TextScaleOption.EXTRA_LARGE.scale)

        // Test fromScale companion helper
        assertEquals(TextScaleOption.SMALL, TextScaleOption.fromScale(0.85f))
        assertEquals(TextScaleOption.STANDARD, TextScaleOption.fromScale(1.0f))
        assertEquals(TextScaleOption.LARGE, TextScaleOption.fromScale(1.2f))
        assertEquals(TextScaleOption.EXTRA_LARGE, TextScaleOption.fromScale(1.35f))
    }

    @Test
    fun testReadingLineSpacingOptions() {
        val spacings = ReadingLineSpacing.entries
        assertEquals(3, spacings.size)

        assertEquals(1.2f, ReadingLineSpacing.COMPACT.multiplier, 0.01f)
        assertEquals(1.4f, ReadingLineSpacing.STANDARD.multiplier, 0.01f)
        assertEquals(1.7f, ReadingLineSpacing.RELAXED.multiplier, 0.01f)

        assertEquals("Compact (1.2x)", ReadingLineSpacing.COMPACT.label)
        assertEquals("Standard (1.4x)", ReadingLineSpacing.STANDARD.label)
        assertEquals("Relaxed (1.7x)", ReadingLineSpacing.RELAXED.label)
    }

    @Test
    fun testExplanationLevelIntegrity() {
        val levels = ExplanationLevel.entries
        assertEquals(3, levels.size)

        val beginner = ExplanationLevel.BEGINNER
        val intermediate = ExplanationLevel.INTERMEDIATE
        val advanced = ExplanationLevel.ADVANCED

        assertTrue(beginner.description.contains("intuitive"))
        assertTrue(intermediate.description.contains("depth"))
        assertTrue(advanced.description.contains("biophysics"))
    }

    @Test
    fun testMcqDifficultyIntegrity() {
        val difficulties = McqDifficulty.entries
        assertEquals(4, difficulties.size)

        assertTrue(difficulties.contains(McqDifficulty.EASY))
        assertTrue(difficulties.contains(McqDifficulty.MODERATE))
        assertTrue(difficulties.contains(McqDifficulty.HARD))
        assertTrue(difficulties.contains(McqDifficulty.MIXED))

        assertEquals("Mixed Difficulty", McqDifficulty.MIXED.displayName)
    }

    @Test
    fun testSettingsSevenCategoriesCovered() {
        val categories = SettingsCategoryType.entries
        assertEquals("Must strictly cover 7 categories as defined by specification", 7, categories.size)

        assertTrue(categories.contains(SettingsCategoryType.APPEARANCE))
        assertTrue(categories.contains(SettingsCategoryType.LEARNING_PREFERENCES))
        assertTrue(categories.contains(SettingsCategoryType.STUDY_REMINDERS))
        assertTrue(categories.contains(SettingsCategoryType.OFFLINE_STORAGE))
        assertTrue(categories.contains(SettingsCategoryType.PROGRESS_AND_DATA))
        assertTrue(categories.contains(SettingsCategoryType.AI_AND_DISCLAIMER))
        assertTrue(categories.contains(SettingsCategoryType.ABOUT_AND_LICENSES))
    }

    @Test
    fun testProgressExportSummaryContent() {
        val summary = SettingsHelper.generateProgressExportSummary(
            pathway = "Academic Ethiopian BSc Curriculum",
            studyLevel = "Clinical Anesthesia Practice",
            academicYear = "Year III",
            completedLessonIds = setOf("lesson_anes_eval_1", "lesson_airway_eval_1"),
            totalLessonsCount = 24,
            notesCount = 3,
            bookmarksCount = 5,
            streakDays = 7
        )

        assertNotNull(summary)
        assertTrue("Export must contain official curriculum reference", summary.contains("Ethiopian BSc Curriculum"))
        assertTrue("Export must contain completed lessons count", summary.contains("Completed Core Lessons: 2 of 24"))
        assertTrue("Export must contain bookmarks count", summary.contains("Bookmarked Resources: 5"))
        assertTrue("Export must contain notes count", summary.contains("Saved Study Notes: 3"))
        assertTrue("Export must contain study streak", summary.contains("Study Streak: 7 days"))
        assertTrue("Export must contain pathway", summary.contains("Academic Ethiopian BSc Curriculum"))
        assertTrue("Export must contain study year", summary.contains("Year III"))
        assertTrue("Export must contain lesson IDs", summary.contains("• lesson_anes_eval_1"))
    }

    @Test
    fun testRepositoryPreferenceKeysIntegrity() {
        assertEquals("setting_theme_mode", AnesthesiaRepository.PREF_THEME_MODE)
        assertEquals("setting_text_scale", AnesthesiaRepository.PREF_TEXT_SCALE)
        assertEquals("setting_line_spacing", AnesthesiaRepository.PREF_LINE_SPACING)
        assertEquals("setting_high_contrast", AnesthesiaRepository.PREF_HIGH_CONTRAST)
        assertEquals("setting_explanation_level", AnesthesiaRepository.PREF_EXPLANATION_LEVEL)
        assertEquals("setting_mcq_difficulty", AnesthesiaRepository.PREF_MCQ_DIFFICULTY)
        assertEquals("setting_default_question_count", AnesthesiaRepository.PREF_DEFAULT_QUESTION_COUNT)
        assertEquals("setting_show_explanations_immediately", AnesthesiaRepository.PREF_SHOW_EXPLANATIONS_IMMEDIATELY)
        assertEquals("setting_show_learning_objectives", AnesthesiaRepository.PREF_SHOW_LEARNING_OBJECTIVES)
        assertEquals("setting_study_reminder_enabled", AnesthesiaRepository.PREF_STUDY_REMINDER_ENABLED)
        assertEquals("setting_study_reminder_time", AnesthesiaRepository.PREF_STUDY_REMINDER_TIME)
        assertEquals("setting_study_reminder_days", AnesthesiaRepository.PREF_STUDY_REMINDER_DAYS)
    }

    @Test
    fun testClearCacheDirectoryLogic() {
        val tempCacheDir = File(System.getProperty("java.io.tmpdir"), "anesthesia_test_cache_${System.currentTimeMillis()}")
        tempCacheDir.mkdirs()

        val sampleFile1 = File(tempCacheDir, "temp_img_1.tmp")
        sampleFile1.writeText("sample cached image bytes")
        val sampleSubDir = File(tempCacheDir, "sub_cache")
        sampleSubDir.mkdirs()
        val sampleFile2 = File(sampleSubDir, "temp_data.bin")
        sampleFile2.writeText("more cache data")

        assertTrue(sampleFile1.exists())
        assertTrue(sampleFile2.exists())

        tempCacheDir.listFiles()?.forEach { file ->
            file.deleteRecursively()
        }

        assertFalse("Sample cache file 1 should be deleted", sampleFile1.exists())
        assertFalse("Sample cache sub-directory should be deleted", sampleSubDir.exists())
        assertTrue("Root cache directory itself remains intact", tempCacheDir.exists())

        tempCacheDir.delete()
    }
}
