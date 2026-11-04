package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.StatusBadge
import com.example.ui.theme.*
import com.example.ui.viewmodel.AnesthesiaViewModel

// ==========================================
// 4. OFFLINE STORAGE & CACHE DIALOG
// ==========================================
@Composable
fun OfflineStorageDialog(
    viewModel: AnesthesiaViewModel,
    onDismiss: () -> Unit
) {
    val storageInfo = remember { viewModel.getStorageUsageInfo() }
    var showConfirmClearCache by remember { mutableStateOf(false) }
    var cacheClearedMessage by remember { mutableStateOf<String?>(null) }

    if (showConfirmClearCache) {
        AlertDialog(
            onDismissRequest = { showConfirmClearCache = false },
            title = { Text("Clear Temporary Cache?", fontWeight = FontWeight.Bold, color = Color.White) },
            text = {
                Text(
                    "This safely clears cached layout calculations and temporary image bitmaps (${storageInfo.formattedCache}).\n\nYour saved notes, bookmarks, completed lessons, and study streak will NOT be affected.",
                    color = TextSecondary
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        val freed = viewModel.clearCache()
                        showConfirmClearCache = false
                        cacheClearedMessage = "Cleared temporary cache successfully."
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = AlertAmber, contentColor = DeepObsidian)
                ) {
                    Text("Clear Cache")
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfirmClearCache = false }) {
                    Text("Cancel", color = TextSecondary)
                }
            },
            containerColor = NavySurface
        )
    }

    SettingsBaseDialog(
        title = "Offline Content & Storage",
        icon = Icons.Filled.SdCard,
        onDismiss = onDismiss
    ) {
        Text("Device Storage Usage", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = SurgicalCyan)
        Spacer(modifier = Modifier.height(8.dp))

        Surface(shape = RoundedCornerShape(10.dp), color = NavySurfaceVariant, modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Total App Storage:", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                    Text(storageInfo.formattedTotal, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = SurgicalCyan)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Temporary Cache:", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                    Text(storageInfo.formattedCache, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text("Offline Curricular Resources (100% Bundled)", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = ClinicalTeal)
        Spacer(modifier = Modifier.height(8.dp))

        val items = listOf(
            Triple("Curriculum Modules", "${storageInfo.offlineLessonsCount} Core Lessons", Icons.Filled.AccountTree),
            Triple("Pharmacology Monographs", "${storageInfo.offlineDrugsCount} Comprehensive Drugs", Icons.Filled.Medication),
            Triple("Surgical Protocols", "${storageInfo.offlineGuidesCount} Specialty Guides", Icons.Filled.MedicalServices),
            Triple("Anatomy Illustrations", "${storageInfo.offlineAnatomyCount} Verified 2D Systems", Icons.Filled.Biotech)
        )

        items.forEach { (name, count, icon) ->
            Surface(shape = RoundedCornerShape(8.dp), color = NavySurfaceVariant.copy(alpha = 0.5f), modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)) {
                Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = icon, contentDescription = null, tint = ClinicalTeal, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(name, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Text(count, style = MaterialTheme.typography.bodySmall, color = TextMuted, fontSize = 11.sp)
                    }
                    StatusBadge(status = "OFFLINE VERIFIED", color = VitalGreen)
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        if (cacheClearedMessage != null) {
            Surface(shape = RoundedCornerShape(8.dp), color = VitalGreen.copy(alpha = 0.15f), border = androidx.compose.foundation.BorderStroke(1.dp, VitalGreen), modifier = Modifier.fillMaxWidth()) {
                Text(cacheClearedMessage!!, style = MaterialTheme.typography.bodySmall, color = VitalGreen, modifier = Modifier.padding(10.dp), fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        OutlinedButton(
            onClick = { showConfirmClearCache = true },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = AlertAmber),
            border = androidx.compose.foundation.BorderStroke(1.dp, AlertAmber.copy(alpha = 0.6f)),
            modifier = Modifier.fillMaxWidth().minimumInteractiveComponentSize().testTag("clear_cache_button")
        ) {
            Icon(imageVector = Icons.Filled.CleaningServices, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Clear Temporary Cache (${storageInfo.formattedCache})")
        }
    }
}

// ==========================================
// 5. PROGRESS & PERSONAL DATA DIALOG
// ==========================================
@Composable
fun ProgressAndDataDialog(
    viewModel: AnesthesiaViewModel,
    onDismiss: () -> Unit
) {
    val completedIds by viewModel.completedLessonIds.collectAsState()
    val allNotes by viewModel.allNotes.collectAsState()
    val bookmarks by viewModel.bookmarks.collectAsState()
    val streakDays by viewModel.studyStreakDays.collectAsState()
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    var showConfirmResetPreferences by remember { mutableStateOf(false) }
    var showConfirmResetProgress by remember { mutableStateOf(false) }
    var showConfirmClearNotes by remember { mutableStateOf(false) }
    var feedbackMessage by remember { mutableStateOf<String?>(null) }

    if (showConfirmResetPreferences) {
        AlertDialog(
            onDismissRequest = { showConfirmResetPreferences = false },
            title = { Text("Reset Preferences to Defaults?", fontWeight = FontWeight.Bold, color = Color.White) },
            text = { Text("This resets display theme, text scaling, explanation level, and quiz count to default. Your notes, bookmarks, and completed lessons will NOT be changed.", color = TextSecondary) },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.resetPreferencesToDefault()
                        showConfirmResetPreferences = false
                        feedbackMessage = "Settings restored to defaults."
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = SurgicalCyan, contentColor = DeepObsidian)
                ) { Text("Reset Preferences") }
            },
            dismissButton = { TextButton(onClick = { showConfirmResetPreferences = false }) { Text("Cancel", color = TextSecondary) } },
            containerColor = NavySurface
        )
    }

    if (showConfirmResetProgress) {
        AlertDialog(
            onDismissRequest = { showConfirmResetProgress = false },
            title = { Text("Reset Curriculum Progress?", fontWeight = FontWeight.Bold, color = AlarmRose) },
            text = { Text("Are you sure? This resets your study streak and unchecks all completed lessons. Your notes and bookmarks will be kept.", color = TextSecondary) },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.resetLearnerProgress()
                        showConfirmResetProgress = false
                        feedbackMessage = "Curriculum progress cleared to 0%."
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = AlarmRose, contentColor = Color.White)
                ) { Text("Reset Progress") }
            },
            dismissButton = { TextButton(onClick = { showConfirmResetProgress = false }) { Text("Cancel", color = TextSecondary) } },
            containerColor = NavySurface
        )
    }

    if (showConfirmClearNotes) {
        AlertDialog(
            onDismissRequest = { showConfirmClearNotes = false },
            title = { Text("Delete All Saved Notes?", fontWeight = FontWeight.Bold, color = AlarmRose) },
            text = { Text("This permanently deletes all ${allNotes.size} study notes stored on this device. This action cannot be undone.", color = TextSecondary) },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.clearAllLearnerNotes()
                        showConfirmClearNotes = false
                        feedbackMessage = "All study notes deleted."
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = AlarmRose, contentColor = Color.White)
                ) { Text("Delete Notes") }
            },
            dismissButton = { TextButton(onClick = { showConfirmClearNotes = false }) { Text("Cancel", color = TextSecondary) } },
            containerColor = NavySurface
        )
    }

    SettingsBaseDialog(
        title = "Learning Progress & Data",
        icon = Icons.Filled.FolderShared,
        onDismiss = onDismiss
    ) {
        Text("Learner Record Summary", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = SurgicalCyan)
        Spacer(modifier = Modifier.height(6.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            DataStatCard("Completed", "${completedIds.size}", ClinicalTeal, Modifier.weight(1f))
            DataStatCard("Streak", "$streakDays d", AlertAmber, Modifier.weight(1f))
            DataStatCard("Notes", "${allNotes.size}", SurgicalCyan, Modifier.weight(1f))
            DataStatCard("Saved", "${bookmarks.size}", VitalGreen, Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(14.dp))

        Button(
            onClick = {
                val data = viewModel.exportLearnerData()
                clipboardManager.setText(AnnotatedString(data))
                feedbackMessage = "Learner summary copied to clipboard!"
                Toast.makeText(context, "Progress export copied to clipboard", Toast.LENGTH_SHORT).show()
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = SurgicalCyan, contentColor = DeepObsidian),
            modifier = Modifier.fillMaxWidth().minimumInteractiveComponentSize().testTag("export_data_button")
        ) {
            Icon(imageVector = Icons.Filled.Share, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Export Learning Portfolio (Clipboard)")
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (feedbackMessage != null) {
            Surface(shape = RoundedCornerShape(8.dp), color = VitalGreen.copy(alpha = 0.15f), border = androidx.compose.foundation.BorderStroke(1.dp, VitalGreen), modifier = Modifier.fillMaxWidth()) {
                Text(feedbackMessage!!, style = MaterialTheme.typography.bodySmall, color = VitalGreen, modifier = Modifier.padding(10.dp), fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        Text("Management & Reset Controls", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = AlertAmber)
        Spacer(modifier = Modifier.height(6.dp))

        OutlinedButton(
            onClick = { showConfirmResetPreferences = true },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
            modifier = Modifier.fillMaxWidth().minimumInteractiveComponentSize().testTag("reset_preferences_button")
        ) {
            Icon(imageVector = Icons.Filled.Restore, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Reset App Preferences to Default")
        }

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedButton(
            onClick = { showConfirmResetProgress = true },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = AlarmRose),
            border = androidx.compose.foundation.BorderStroke(1.dp, AlarmRose.copy(alpha = 0.5f)),
            modifier = Modifier.fillMaxWidth().minimumInteractiveComponentSize().testTag("reset_progress_button")
        ) {
            Icon(imageVector = Icons.Filled.RestartAlt, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Reset Completed Lesson Progress (0%)")
        }

        if (allNotes.isNotEmpty()) {
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedButton(
                onClick = { showConfirmClearNotes = true },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = AlarmRose),
                border = androidx.compose.foundation.BorderStroke(1.dp, AlarmRose.copy(alpha = 0.5f)),
                modifier = Modifier.fillMaxWidth().minimumInteractiveComponentSize()
            ) {
                Icon(imageVector = Icons.Filled.DeleteSweep, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Delete All Saved Notes (${allNotes.size})")
            }
        }
    }
}

// ==========================================
// 6. AI & CLINICAL DISCLAIMER DIALOG
// ==========================================
@Composable
fun AiAndDisclaimerDialog(
    onDismiss: () -> Unit
) {
    SettingsBaseDialog(
        title = "AI Logic & Medical Disclaimer",
        icon = Icons.Filled.HealthAndSafety,
        onDismiss = onDismiss
    ) {
        Text("Data Privacy & On-Device Processing", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = VitalGreen)
        Spacer(modifier = Modifier.height(6.dp))

        Surface(shape = RoundedCornerShape(10.dp), color = NavySurfaceVariant, modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Filled.Security, contentDescription = null, tint = VitalGreen, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("100% Deterministic Offline Medical Logic", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = Color.White)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Anesthesia Pro operates strictly on-device without remote servers. All clinical calculators, drug dosages, pharmacokinetic curves, and curriculum questions use deterministic mathematical and medical formulas.\n\nNo student inputs, patient parameters, search terms, or notes are uploaded or monitored.",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary,
                    fontSize = 11.5.sp,
                    lineHeight = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text("Official Educational & Clinical Notice", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = AlertAmber)
        Spacer(modifier = Modifier.height(6.dp))

        Surface(shape = RoundedCornerShape(10.dp), color = NavySurfaceVariant, modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "Anesthesia Pro is designed as a structured educational reference for Ethiopian Generic BSc in Anesthesia students, residents, and certified anesthesia practitioners.\n\n" +
                            "CRITICAL NOTICE:\n" +
                            "• This application does NOT replace clinical judgment or direct patient evaluation.\n" +
                            "• It does NOT substitute for senior consultant supervision in operating rooms or intensive care units.\n" +
                            "• Institutional protocols and local hospital drug availability always take precedence over application suggestions.\n" +
                            "• In emergency crises (CICO, cardiac arrest, LAST, malignant hyperthermia), immediate physical emergency response and calling for experienced help take absolute priority.",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary,
                    fontSize = 11.5.sp,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

// ==========================================
// 7. ABOUT, SOURCES & LICENSES DIALOG
// ==========================================
@Composable
fun AboutAndLicensesDialog(
    onDismiss: () -> Unit
) {
    SettingsBaseDialog(
        title = "About, Sources & Licenses",
        icon = Icons.Filled.Info,
        onDismiss = onDismiss
    ) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(NavySurfaceVariant)
                    .border(1.5.dp, SurgicalCyan, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Filled.LocalHospital, contentDescription = null, tint = SurgicalCyan, modifier = Modifier.size(30.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("ANESTHESIA PRO", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.ExtraBold, color = Color.White, letterSpacing = 1.sp)
            Text("Version 1.5.0 (Build 2026)", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
            Text("Ethiopian BSc Anesthesia Platform", style = MaterialTheme.typography.labelSmall, color = ClinicalTeal)
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text("Curricular Authority & Framework", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = SurgicalCyan)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Ministry of Health (MoH) & Ministry of Science and Higher Education (MoSHE), Ethiopia:\n" +
                    "\"National Harmonized Modular Curriculum for Generic Bachelor of Science in Anesthesia\", February 2021 Framework.",
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary,
            fontSize = 11.5.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text("Primary Clinical Literature Sources", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = ClinicalTeal)
        Spacer(modifier = Modifier.height(4.dp))
        val sources = listOf(
            "Miller's Anesthesia, 9th Edition (Gropper et al.)",
            "Morgan & Mikhail's Clinical Anesthesiology, 7th Edition (Butterworth et al.)",
            "Barash's Clinical Anesthesia, 8th Edition (Barash et al.)",
            "Stoelting's Pharmacology & Physiology in Anesthetic Practice, 5th Edition",
            "American Society of Anesthesiologists (ASA) Standards & Guidelines",
            "Difficult Airway Society (DAS) 2015 Guidelines",
            "American Society of Regional Anesthesia (ASRA) Anticoagulation & LAST Protocols",
            "Malignant Hyperthermia Association of the United States (MHAUS)"
        )
        sources.forEach { src ->
            Text("• $src", style = MaterialTheme.typography.bodySmall, color = TextMuted, fontSize = 11.sp, modifier = Modifier.padding(vertical = 1.dp))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text("Open-Source Licenses", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = AlertAmber)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "• Android Jetpack & Jetpack Compose (Apache License 2.0)\n" +
                    "• Kotlin Coroutines & Flow (Apache License 2.0)\n" +
                    "• Room SQLite Database (Apache License 2.0)\n" +
                    "• Material Design 3 (Apache License 2.0)",
            style = MaterialTheme.typography.bodySmall,
            color = TextMuted,
            fontSize = 11.sp
        )
    }
}

@Composable
private fun DataStatCard(label: String, value: String, color: Color, modifier: Modifier = Modifier) {
    Surface(shape = RoundedCornerShape(8.dp), color = NavySurfaceVariant, modifier = modifier) {
        Column(modifier = Modifier.padding(vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(value, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = color)
            Text(label, style = MaterialTheme.typography.labelSmall, color = TextMuted, fontSize = 10.sp)
        }
    }
}
