package com.example.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class AppThemeMode(val displayName: String, val description: String) {
    SYSTEM("System Default", "Follows device appearance schedule"),
    DARK("Clinical Dark", "Optimized for low-light surgical environments and battery efficiency"),
    LIGHT("Medical Light", "High-clarity daytime reading contrast")
}

enum class TextScaleOption(val scale: Float, val label: String) {
    SMALL(0.85f, "Small (85%)"),
    STANDARD(1.0f, "Standard (100%)"),
    LARGE(1.2f, "Large (120%)"),
    EXTRA_LARGE(1.35f, "Extra Large (135%)");

    companion object {
        fun fromScale(scale: Float): TextScaleOption {
            return entries.minByOrNull { kotlin.math.abs(it.scale - scale) } ?: STANDARD
        }
    }
}

enum class ReadingLineSpacing(val multiplier: Float, val label: String) {
    COMPACT(1.2f, "Compact (1.2x)"),
    STANDARD(1.4f, "Standard (1.4x)"),
    RELAXED(1.7f, "Relaxed (1.7x)")
}

enum class ExplanationLevel(val displayName: String, val description: String) {
    BEGINNER("Beginner Focus", "Emphasizes intuitive clinical overviews and fundamentals"),
    INTERMEDIATE("Balanced Curriculum", "Balances foundational concepts with physiological depth"),
    ADVANCED("Advanced Subspecialty", "Prioritizes in-depth biophysics, pharmacology kinetics, and crisis algorithms")
}

enum class McqDifficulty(val displayName: String, val description: String) {
    EASY("Basic Sciences", "Foundational physiology and standard drug pharmacology"),
    MODERATE("Clinical Core", "Standard perioperative cases and anesthesia machine physics"),
    HARD("Board Level", "Complex multi-system trauma, crises, and obscure exam distinctions"),
    MIXED("Mixed Difficulty", "Randomized cross-spectrum question difficulty")
}

data class StorageUsageInfo(
    val internalFilesBytes: Long,
    val cacheBytes: Long,
    val databaseBytes: Long,
    val totalBytes: Long,
    val formattedTotal: String,
    val formattedCache: String,
    val offlineLessonsCount: Int,
    val offlineDrugsCount: Int,
    val offlineGuidesCount: Int,
    val offlineAnatomyCount: Int
)

enum class SettingsCategoryType {
    APPEARANCE,
    LEARNING_PREFERENCES,
    STUDY_REMINDERS,
    OFFLINE_STORAGE,
    PROGRESS_AND_DATA,
    AI_AND_DISCLAIMER,
    ABOUT_AND_LICENSES
}

data class SettingsCategoryItem(
    val type: SettingsCategoryType,
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val currentSettingSummary: String
)
