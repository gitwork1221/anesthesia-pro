package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.SettingsCategoryItem
import com.example.data.model.SettingsCategoryType
import com.example.ui.components.StatusBadge
import com.example.ui.theme.*
import com.example.ui.viewmodel.AnesthesiaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: AnesthesiaViewModel,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val themeMode by viewModel.themeMode.collectAsState()
    val textScaleOpt by viewModel.textScaleOption.collectAsState()
    val pathway by viewModel.pathway.collectAsState()
    val explanationLevel by viewModel.explanationLevel.collectAsState()
    val reminderEnabled by viewModel.studyReminderEnabled.collectAsState()
    val reminderTime by viewModel.studyReminderTime.collectAsState()
    val completedIds by viewModel.completedLessonIds.collectAsState()
    val allNotes by viewModel.allNotes.collectAsState()
    val allLessons = viewModel.getLessons()

    var activeDialogType by remember { mutableStateOf<SettingsCategoryType?>(null) }

    // Render active dialog when selected
    when (activeDialogType) {
        SettingsCategoryType.APPEARANCE -> {
            AppearanceSettingsDialog(viewModel = viewModel, onDismiss = { activeDialogType = null })
        }
        SettingsCategoryType.LEARNING_PREFERENCES -> {
            LearningPreferencesDialog(viewModel = viewModel, onDismiss = { activeDialogType = null })
        }
        SettingsCategoryType.STUDY_REMINDERS -> {
            StudyRemindersDialog(viewModel = viewModel, onDismiss = { activeDialogType = null })
        }
        SettingsCategoryType.OFFLINE_STORAGE -> {
            OfflineStorageDialog(viewModel = viewModel, onDismiss = { activeDialogType = null })
        }
        SettingsCategoryType.PROGRESS_AND_DATA -> {
            ProgressAndDataDialog(viewModel = viewModel, onDismiss = { activeDialogType = null })
        }
        SettingsCategoryType.AI_AND_DISCLAIMER -> {
            AiAndDisclaimerDialog(onDismiss = { activeDialogType = null })
        }
        SettingsCategoryType.ABOUT_AND_LICENSES -> {
            AboutAndLicensesDialog(onDismiss = { activeDialogType = null })
        }
        null -> {}
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Settings & Preferences",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "App configuration, storage & clinical governance",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("settings_back_button").minimumInteractiveComponentSize()
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = SurgicalCyan
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = NavySurface)
            )
        },
        containerColor = DeepObsidian,
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header Overview Card
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = NavySurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                    modifier = Modifier.fillMaxWidth().testTag("settings_overview_card")
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .background(NavySurfaceVariant)
                                        .border(1.dp, SurgicalCyan, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Tune,
                                        contentDescription = null,
                                        tint = SurgicalCyan,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = "ANESTHESIA PRO",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Version 1.5.0 • Verified Offline",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = TextSecondary,
                                        fontSize = 11.sp
                                    )
                                }
                            }
                            StatusBadge(status = themeMode.displayName, color = SurgicalCyan)
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        HorizontalDivider(color = BorderSubtle)

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            QuickStatusIndicator(label = "Curriculum", value = pathway.displayName.take(13), color = ClinicalTeal)
                            QuickStatusIndicator(label = "Scale", value = textScaleOpt.label.take(5), color = AlertAmber)
                            QuickStatusIndicator(
                                label = "Progress",
                                value = "${completedIds.size}/${allLessons.size}",
                                color = VitalGreen
                            )
                            QuickStatusIndicator(
                                label = "Reminders",
                                value = if (reminderEnabled) reminderTime else "Off",
                                color = if (reminderEnabled) SurgicalCyan else TextMuted
                            )
                        }
                    }
                }
            }

            // Categories List
            val categories = listOf(
                SettingsCategoryItem(
                    type = SettingsCategoryType.APPEARANCE,
                    title = "Appearance & Accessibility",
                    subtitle = "Theme mode (Dark/Light/System), text scale, line spacing & contrast",
                    icon = Icons.Filled.Palette,
                    currentSettingSummary = "${themeMode.displayName} • ${textScaleOpt.label}"
                ),
                SettingsCategoryItem(
                    type = SettingsCategoryType.LEARNING_PREFERENCES,
                    title = "Learning & Exam Preferences",
                    subtitle = "Degree track, explanation depth, question count & exam difficulty",
                    icon = Icons.Filled.School,
                    currentSettingSummary = "${pathway.displayName.take(16)} • ${explanationLevel.displayName}"
                ),
                SettingsCategoryItem(
                    type = SettingsCategoryType.STUDY_REMINDERS,
                    title = "Study Schedule & Reminders",
                    subtitle = "On-device daily study schedule and habit alerts",
                    icon = Icons.Filled.NotificationsActive,
                    currentSettingSummary = if (reminderEnabled) "Active at $reminderTime" else "Disabled"
                ),
                SettingsCategoryItem(
                    type = SettingsCategoryType.OFFLINE_STORAGE,
                    title = "Offline Content & Storage",
                    subtitle = "Storage breakdown, bundled curriculum inventory & cache cleaning",
                    icon = Icons.Filled.SdCard,
                    currentSettingSummary = "100% Pre-installed • Offline"
                ),
                SettingsCategoryItem(
                    type = SettingsCategoryType.PROGRESS_AND_DATA,
                    title = "Learning Progress & Personal Data",
                    subtitle = "Export learning portfolio, reset preferences, or clear learner records",
                    icon = Icons.Filled.FolderShared,
                    currentSettingSummary = "${completedIds.size} completed • ${allNotes.size} notes"
                ),
                SettingsCategoryItem(
                    type = SettingsCategoryType.AI_AND_DISCLAIMER,
                    title = "AI Logic & Medical Disclaimer",
                    subtitle = "Deterministic offline logic disclosure & clinical supervision notice",
                    icon = Icons.Filled.HealthAndSafety,
                    currentSettingSummary = "100% On-Device • Zero Cloud"
                ),
                SettingsCategoryItem(
                    type = SettingsCategoryType.ABOUT_AND_LICENSES,
                    title = "About, Sources & Licenses",
                    subtitle = "Ethiopian Feb 2021 framework attribution & clinical references",
                    icon = Icons.Filled.Info,
                    currentSettingSummary = "v1.5.0 • MoH & MoSHE"
                )
            )

            items(categories, key = { it.type }) { category ->
                SettingsCategoryCard(
                    item = category,
                    onClick = { activeDialogType = category.type }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun SettingsCategoryCard(
    item: SettingsCategoryItem,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .testTag("settings_category_${item.type.name.lowercase()}")
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(NavySurfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = SurgicalCyan,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = item.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary,
                    fontSize = 11.5.sp,
                    lineHeight = 15.sp,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = item.currentSettingSummary,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = ClinicalTeal,
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = "Open ${item.title}",
                tint = TextSecondary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun QuickStatusIndicator(
    label: String,
    value: String,
    color: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = color)
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = TextMuted, fontSize = 10.sp)
    }
}
