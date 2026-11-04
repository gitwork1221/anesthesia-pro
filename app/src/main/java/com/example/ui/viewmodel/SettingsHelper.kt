package com.example.ui.viewmodel

import android.content.Context
import com.example.data.model.StorageUsageInfo
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object SettingsHelper {

    fun calculateStorageUsage(
        context: Context,
        lessonsCount: Int,
        drugsCount: Int,
        guidesCount: Int,
        anatomyCount: Int
    ): StorageUsageInfo {
        val filesSize = getDirectorySize(context.filesDir)
        val cacheSize = getDirectorySize(context.cacheDir)
        val dbFile = context.getDatabasePath("anesthesia_pro_db")
        val dbSize = if (dbFile.exists()) dbFile.length() else 0L
        val total = filesSize + cacheSize + dbSize

        return StorageUsageInfo(
            internalFilesBytes = filesSize,
            cacheBytes = cacheSize,
            databaseBytes = dbSize,
            totalBytes = total,
            formattedTotal = formatBytes(total),
            formattedCache = formatBytes(cacheSize),
            offlineLessonsCount = lessonsCount,
            offlineDrugsCount = drugsCount,
            offlineGuidesCount = guidesCount,
            offlineAnatomyCount = anatomyCount
        )
    }

    fun clearCache(context: Context): Long {
        val cacheDir = context.cacheDir
        val bytesBefore = getDirectorySize(cacheDir)
        runCatching {
            cacheDir.listFiles()?.forEach { file ->
                file.deleteRecursively()
            }
        }
        return bytesBefore
    }

    fun generateProgressExportSummary(
        pathway: String,
        studyLevel: String,
        academicYear: String,
        completedLessonIds: Set<String>,
        totalLessonsCount: Int,
        notesCount: Int,
        bookmarksCount: Int,
        streakDays: Int
    ): String {
        return buildString {
            appendLine("==========================================")
            appendLine("ANESTHESIA PRO — LEARNER PROGRESS EXPORT")
            appendLine("==========================================")
            appendLine("Generated: ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Date())}")
            appendLine("Degree Track: $pathway")
            appendLine("Academic Stage: $academicYear ($studyLevel)")
            appendLine("Study Streak: $streakDays days")
            appendLine()
            appendLine("--- CURRICULUM PROGRESS ---")
            val pct = if (totalLessonsCount > 0) (completedLessonIds.size * 100 / totalLessonsCount) else 0
            appendLine("Completed Core Lessons: ${completedLessonIds.size} of $totalLessonsCount ($pct%)")
            appendLine("Saved Study Notes: $notesCount")
            appendLine("Bookmarked Resources: $bookmarksCount")
            appendLine()
            appendLine("--- COMPLETED LESSON RECORDS ---")
            if (completedLessonIds.isEmpty()) {
                appendLine("(No completed lessons yet)")
            } else {
                completedLessonIds.forEach { id -> appendLine("• $id") }
            }
            appendLine("==========================================")
            appendLine("Certified Offline Verification • Ethiopian BSc Curriculum (Feb 2021)")
            appendLine("==========================================")
        }
    }

    private fun getDirectorySize(dir: File?): Long {
        if (dir == null || !dir.exists()) return 0L
        return runCatching {
            dir.walkTopDown().filter { it.isFile }.sumOf { it.length() }
        }.getOrDefault(0L)
    }

    private fun formatBytes(bytes: Long): String {
        if (bytes < 1024) return "$bytes B"
        val kb = bytes / 1024.0
        if (kb < 1024) return "%.1f KB".format(kb)
        val mb = kb / 1024.0
        return "%.2f MB".format(mb)
    }
}
