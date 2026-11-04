package com.example.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.model.*
import com.example.ui.theme.*
import com.example.ui.viewmodel.AnesthesiaViewModel

// ==========================================
// 1. APPEARANCE & ACCESSIBILITY DIALOG
// ==========================================
@Composable
fun AppearanceSettingsDialog(
    viewModel: AnesthesiaViewModel,
    onDismiss: () -> Unit
) {
    val themeMode by viewModel.themeMode.collectAsState()
    val textScaleOpt by viewModel.textScaleOption.collectAsState()
    val lineSpacing by viewModel.readingLineSpacing.collectAsState()
    val highContrast by viewModel.highContrastMode.collectAsState()

    SettingsBaseDialog(
        title = "Appearance & Accessibility",
        icon = Icons.Filled.Palette,
        onDismiss = onDismiss
    ) {
        Text("Theme Mode", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = SurgicalCyan)
        Spacer(modifier = Modifier.height(6.dp))

        AppThemeMode.entries.forEach { mode ->
            val isSelected = themeMode == mode
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = if (isSelected) NavySurfaceVariant else Color.Transparent,
                border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) SurgicalCyan else BorderSubtle),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { viewModel.setThemeMode(mode) }
                    .testTag("theme_option_${mode.name}")
            ) {
                Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = isSelected,
                        onClick = { viewModel.setThemeMode(mode) },
                        colors = RadioButtonDefaults.colors(selectedColor = SurgicalCyan)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(mode.displayName, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Text(mode.description, style = MaterialTheme.typography.bodySmall, color = TextSecondary, fontSize = 11.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text("Text Sizing (${textScaleOpt.label})", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = SurgicalCyan)
        Spacer(modifier = Modifier.height(6.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            TextScaleOption.entries.forEach { opt ->
                val isSelected = textScaleOpt == opt
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isSelected) SurgicalCyan.copy(alpha = 0.2f) else NavySurfaceVariant,
                    border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) SurgicalCyan else BorderSubtle),
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { viewModel.setTextScaleOption(opt) }
                        .testTag("text_scale_${opt.name}")
                ) {
                    Column(modifier = Modifier.padding(vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = when (opt) {
                                TextScaleOption.SMALL -> "85%"
                                TextScaleOption.STANDARD -> "100%"
                                TextScaleOption.LARGE -> "120%"
                                TextScaleOption.EXTRA_LARGE -> "135%"
                            },
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) SurgicalCyan else Color.White
                        )
                        Text(opt.name.take(3), style = MaterialTheme.typography.labelSmall, color = TextMuted, fontSize = 9.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text("Reading Line Spacing", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = SurgicalCyan)
        Spacer(modifier = Modifier.height(6.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            ReadingLineSpacing.entries.forEach { sp ->
                val isSelected = lineSpacing == sp
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isSelected) ClinicalTeal.copy(alpha = 0.2f) else NavySurfaceVariant,
                    border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) ClinicalTeal else BorderSubtle),
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { viewModel.setReadingLineSpacing(sp) }
                ) {
                    Text(
                        text = sp.label,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) ClinicalTeal else Color.White,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
                        maxLines = 1
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("High Contrast Outlines", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = Color.White)
                Text("Sharper borders for bright surgical rooms", style = MaterialTheme.typography.bodySmall, color = TextSecondary, fontSize = 11.sp)
            }
            Switch(
                checked = highContrast,
                onCheckedChange = { viewModel.setHighContrastMode(it) },
                colors = SwitchDefaults.colors(checkedThumbColor = SurgicalCyan, checkedTrackColor = NavySurfaceVariant)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Surface(shape = RoundedCornerShape(8.dp), color = NavySurfaceVariant.copy(alpha = 0.5f), modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.Language, contentDescription = null, tint = ClinicalTeal, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text("Language: English (Medical)", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("Ethiopian health science universities examine anesthesia curriculum in English.", style = MaterialTheme.typography.bodySmall, color = TextMuted, fontSize = 10.5.sp)
                }
            }
        }
    }
}

// ==========================================
// 2. LEARNING PREFERENCES DIALOG
// ==========================================
@Composable
fun LearningPreferencesDialog(
    viewModel: AnesthesiaViewModel,
    onDismiss: () -> Unit
) {
    val pathway by viewModel.pathway.collectAsState()
    val explanationLevel by viewModel.explanationLevel.collectAsState()
    val mcqDifficulty by viewModel.mcqDifficulty.collectAsState()
    val questionCount by viewModel.defaultQuestionCount.collectAsState()
    val showImmediate by viewModel.showExplanationsImmediately.collectAsState()
    val showObjectives by viewModel.showLearningObjectives.collectAsState()

    SettingsBaseDialog(
        title = "Learning & Exam Preferences",
        icon = Icons.Filled.School,
        onDismiss = onDismiss
    ) {
        Text("Primary Educational Track", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = SurgicalCyan)
        Spacer(modifier = Modifier.height(6.dp))

        LearningPathway.entries.forEach { pw ->
            val isSelected = pathway == pw
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = if (isSelected) NavySurfaceVariant else Color.Transparent,
                border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) SurgicalCyan else BorderSubtle),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { viewModel.updatePathway(pw) }
            ) {
                Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = isSelected, onClick = { viewModel.updatePathway(pw) }, colors = RadioButtonDefaults.colors(selectedColor = SurgicalCyan))
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(pw.displayName, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Text(pw.description, style = MaterialTheme.typography.bodySmall, color = TextSecondary, fontSize = 11.sp, maxLines = 2)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text("Lesson Content Depth", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = SurgicalCyan)
        Spacer(modifier = Modifier.height(6.dp))

        ExplanationLevel.entries.forEach { lvl ->
            val isSelected = explanationLevel == lvl
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = if (isSelected) NavySurfaceVariant else Color.Transparent,
                border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) ClinicalTeal else BorderSubtle),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { viewModel.setExplanationLevel(lvl) }
            ) {
                Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = isSelected, onClick = { viewModel.setExplanationLevel(lvl) }, colors = RadioButtonDefaults.colors(selectedColor = ClinicalTeal))
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(lvl.displayName, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Text(lvl.description, style = MaterialTheme.typography.bodySmall, color = TextSecondary, fontSize = 11.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text("MCQ Exam Difficulty", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = SurgicalCyan)
        Spacer(modifier = Modifier.height(6.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            McqDifficulty.entries.forEach { diff ->
                val isSelected = mcqDifficulty == diff
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isSelected) AlertAmber.copy(alpha = 0.2f) else NavySurfaceVariant,
                    border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) AlertAmber else BorderSubtle),
                    modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).clickable { viewModel.setMcqDifficulty(diff) }
                ) {
                    Text(
                        text = diff.displayName,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) AlertAmber else Color.White,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
                        maxLines = 1
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text("Default Quiz Questions: $questionCount", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = Color.White)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf(5, 10, 15, 20).forEach { cnt ->
                val isSelected = questionCount == cnt
                FilterChip(
                    selected = isSelected,
                    onClick = { viewModel.setDefaultQuestionCount(cnt) },
                    label = { Text("$cnt Qs") },
                    colors = FilterChipDefaults.filterChipColors(selectedContainerColor = SurgicalCyan, selectedLabelColor = DeepObsidian)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Instant Answer Rationales", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                Text("Show clinical reasoning immediately on selection", style = MaterialTheme.typography.bodySmall, color = TextSecondary, fontSize = 11.sp)
            }
            Switch(checked = showImmediate, onCheckedChange = { viewModel.setShowExplanationsImmediately(it) })
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Display Learning Objectives", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                Text("Show competencies header at the start of each lesson", style = MaterialTheme.typography.bodySmall, color = TextSecondary, fontSize = 11.sp)
            }
            Switch(checked = showObjectives, onCheckedChange = { viewModel.setShowLearningObjectives(it) })
        }
    }
}

// ==========================================
// 3. STUDY REMINDERS DIALOG
// ==========================================
@Composable
fun StudyRemindersDialog(
    viewModel: AnesthesiaViewModel,
    onDismiss: () -> Unit
) {
    val enabled by viewModel.studyReminderEnabled.collectAsState()
    val reminderTime by viewModel.studyReminderTime.collectAsState()
    val reminderDays by viewModel.studyReminderDays.collectAsState()

    SettingsBaseDialog(
        title = "Study Schedule & Reminders",
        icon = Icons.Filled.NotificationsActive,
        onDismiss = onDismiss
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Daily Study Notifications", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = Color.White)
                Text("Local device alert to maintain curriculum pace", style = MaterialTheme.typography.bodySmall, color = TextSecondary, fontSize = 11.5.sp)
            }
            Switch(
                checked = enabled,
                onCheckedChange = { viewModel.setStudyReminder(it, reminderTime, reminderDays) },
                colors = SwitchDefaults.colors(checkedThumbColor = SurgicalCyan)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text("Target Study Time", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = SurgicalCyan)
        Spacer(modifier = Modifier.height(6.dp))

        val times = listOf("07:00", "08:00", "13:00", "18:00", "20:00", "21:30")
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            times.take(3).forEach { t ->
                val isSel = reminderTime == t
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isSel) SurgicalCyan.copy(alpha = 0.2f) else NavySurfaceVariant,
                    border = androidx.compose.foundation.BorderStroke(1.dp, if (isSel) SurgicalCyan else BorderSubtle),
                    modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).clickable { viewModel.setStudyReminder(enabled, t, reminderDays) }
                ) {
                    Text(t, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = if (isSel) SurgicalCyan else Color.White, modifier = Modifier.padding(vertical = 8.dp), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            times.drop(3).forEach { t ->
                val isSel = reminderTime == t
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isSel) SurgicalCyan.copy(alpha = 0.2f) else NavySurfaceVariant,
                    border = androidx.compose.foundation.BorderStroke(1.dp, if (isSel) SurgicalCyan else BorderSubtle),
                    modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).clickable { viewModel.setStudyReminder(enabled, t, reminderDays) }
                ) {
                    Text(t, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = if (isSel) SurgicalCyan else Color.White, modifier = Modifier.padding(vertical = 8.dp), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text("Active Study Days", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = SurgicalCyan)
        Spacer(modifier = Modifier.height(6.dp))

        val allDays = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            allDays.forEach { d ->
                val isAct = reminderDays.contains(d)
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = if (isAct) ClinicalTeal.copy(alpha = 0.25f) else NavySurfaceVariant,
                    border = androidx.compose.foundation.BorderStroke(0.5.dp, if (isAct) ClinicalTeal else BorderSubtle),
                    modifier = Modifier.weight(1f).clip(RoundedCornerShape(6.dp)).clickable {
                        val upd = if (isAct) reminderDays - d else reminderDays + d
                        viewModel.setStudyReminder(enabled, reminderTime, upd)
                    }
                ) {
                    Text(d.take(2), style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = if (isAct) ClinicalTeal else TextMuted, modifier = Modifier.padding(vertical = 6.dp), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Surface(shape = RoundedCornerShape(8.dp), color = NavySurfaceVariant.copy(alpha = 0.5f), modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = null, tint = SurgicalCyan, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (enabled) "Reminders active for $reminderTime on ${reminderDays.size} days/week. Alerts are scheduled strictly on-device without remote servers." else "Reminders are currently disabled. Toggle the switch above to schedule study alerts.",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary,
                    fontSize = 11.sp
                )
            }
        }
    }
}

// Reusable Base Dialog Container
@Composable
fun SettingsBaseDialog(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onDismiss: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = NavySurface,
            border = androidx.compose.foundation.BorderStroke(1.dp, SurgicalCyan.copy(alpha = 0.4f)),
            modifier = Modifier.fillMaxWidth().padding(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(18.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = icon, contentDescription = null, tint = SurgicalCyan, modifier = Modifier.size(22.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                    IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp).minimumInteractiveComponentSize()) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "Close", tint = TextSecondary)
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = BorderSubtle)

                content()

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = NavySurfaceVariant, contentColor = SurgicalCyan),
                    modifier = Modifier.fillMaxWidth().minimumInteractiveComponentSize()
                ) {
                    Text("Done")
                }
            }
        }
    }
}
