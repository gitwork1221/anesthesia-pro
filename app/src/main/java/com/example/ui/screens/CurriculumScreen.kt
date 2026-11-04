package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AcademicYear
import com.example.data.model.CurriculumMappingStatus
import com.example.data.model.CurriculumModule
import com.example.ui.components.StatusBadge
import com.example.ui.theme.*
import com.example.ui.viewmodel.AnesthesiaViewModel

@Composable
fun CurriculumScreen(
    viewModel: AnesthesiaViewModel,
    onNavigateToLesson: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedYear by viewModel.selectedAcademicYear.collectAsState()
    val completedIds by viewModel.completedLessonIds.collectAsState()
    val bookmarks by viewModel.bookmarks.collectAsState()
    val allLessons = viewModel.getLessons()
    val modulesForYear = viewModel.getModulesByYear(selectedYear)
    var expandedModuleId by remember { mutableStateOf<String?>(modulesForYear.firstOrNull()?.id) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DeepObsidian)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // Title and overall curriculum stats header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Ethiopian BSc Curriculum",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "National Harmonized Framework (Feb 2021) • ${completedIds.size} of ${allLessons.size} Completed",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary,
                    fontSize = 11.5.sp
                )
            }

            Surface(
                shape = RoundedCornerShape(16.dp),
                color = NavySurfaceVariant,
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle)
            ) {
                Text(
                    text = "${(viewModel.getOverallProgressFraction() * 100).toInt()}% Done",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = SurgicalCyan,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Academic Year Selection Tabs (Year I, II, III, IV)
        TabRow(
            selectedTabIndex = selectedYear.ordinal,
            containerColor = NavySurface,
            contentColor = SurgicalCyan,
            divider = { HorizontalDivider(color = BorderSubtle) },
            modifier = Modifier.clip(RoundedCornerShape(10.dp))
        ) {
            AcademicYear.entries.forEach { yr ->
                val isSelected = selectedYear == yr
                Tab(
                    selected = isSelected,
                    onClick = {
                        viewModel.selectedAcademicYear.value = yr
                        val firstMod = viewModel.getModulesByYear(yr).firstOrNull()
                        expandedModuleId = firstMod?.id
                    },
                    text = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Text(
                                text = yr.romanNumeral,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) SurgicalCyan else TextSecondary
                            )
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Current Year Context Banner
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = NavySurface.copy(alpha = 0.7f),
            border = androidx.compose.foundation.BorderStroke(0.5.dp, BorderSubtle),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = "${selectedYear.romanNumeral}: ${selectedYear.title}",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = selectedYear.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary,
                    fontSize = 11.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Modules & Lessons List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            if (modulesForYear.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No modules registered for this academic year.",
                            color = TextMuted,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            items(modulesForYear, key = { it.id }) { module ->
                val isExpanded = expandedModuleId == module.id
                val moduleLessons = viewModel.getLessonsByModule(module.id)
                val moduleCompletedCount = viewModel.getModuleCompletedLessonCount(module.id)
                val moduleProgress = viewModel.getModuleProgressFraction(module.id)

                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = NavySurface),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (isExpanded) SurgicalCyan.copy(alpha = 0.5f) else BorderSubtle
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("module_card_${module.id}")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        // Module Header
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    expandedModuleId = if (isExpanded) null else module.id
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    StatusBadge(
                                        status = when (module.mappingStatus) {
                                            CurriculumMappingStatus.VERIFIED_HARMONIZED -> "VERIFIED"
                                            CurriculumMappingStatus.AWAITING_OFFICIAL_MAPPING -> "AWAITING CODE MAPPING"
                                            CurriculumMappingStatus.SUPPLEMENTARY -> "SUPPLEMENTARY"
                                        },
                                        color = when (module.mappingStatus) {
                                            CurriculumMappingStatus.VERIFIED_HARMONIZED -> VitalGreen
                                            CurriculumMappingStatus.AWAITING_OFFICIAL_MAPPING -> ClinicalTeal
                                            CurriculumMappingStatus.SUPPLEMENTARY -> TextSecondary
                                        }
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    if (module.durationWeeks != null) {
                                        Text(
                                            text = "${module.durationWeeks} Weeks",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = TextMuted
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    text = module.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )

                                if (module.moduleCode != null || module.ects != null) {
                                    Text(
                                        text = "Code: ${module.moduleCode ?: "N/A"} • ${module.ects ?: 0} ECTS",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = TextSecondary
                                    )
                                }
                            }

                            IconButton(
                                onClick = { expandedModuleId = if (isExpanded) null else module.id },
                                modifier = Modifier.minimumInteractiveComponentSize()
                            ) {
                                Icon(
                                    imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                                    tint = SurgicalCyan
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = module.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary,
                            fontSize = 11.5.sp,
                            lineHeight = 16.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Progress indicator for module
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "$moduleCompletedCount of ${moduleLessons.size} lessons completed",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextMuted,
                                fontSize = 10.5.sp
                            )
                            Text(
                                text = "${(moduleProgress * 100).toInt()}%",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (moduleProgress >= 1f) VitalGreen else SurgicalCyan,
                                fontSize = 10.5.sp
                            )
                        }

                        LinearProgressIndicator(
                            progress = { moduleProgress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .clip(RoundedCornerShape(2.dp)),
                            color = if (moduleProgress >= 1f) VitalGreen else SurgicalCyan,
                            trackColor = BorderSubtle
                        )

                        // Expandable Lesson List & Competencies
                        AnimatedVisibility(visible = isExpanded) {
                            Column(modifier = Modifier.padding(top = 12.dp)) {
                                if (module.learningOutcomes.isNotEmpty()) {
                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = NavySurfaceVariant.copy(alpha = 0.5f),
                                        modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
                                    ) {
                                        Column(modifier = Modifier.padding(10.dp)) {
                                            Text(
                                                text = "Key Learning Competencies:",
                                                style = MaterialTheme.typography.labelSmall,
                                                fontWeight = FontWeight.Bold,
                                                color = SurgicalCyan
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            module.learningOutcomes.forEach { outcome ->
                                                Text(
                                                    text = "• $outcome",
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = TextSecondary,
                                                    fontSize = 11.sp,
                                                    lineHeight = 15.sp,
                                                    modifier = Modifier.padding(vertical = 1.dp)
                                                )
                                            }
                                        }
                                    }
                                }

                                Text(
                                    text = "Module Lessons & Practical Units (${moduleLessons.size}):",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                if (moduleLessons.isEmpty()) {
                                    Text(
                                        text = "Core syllabus units under curriculum review. Lessons will be unlocked sequentially.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = TextMuted,
                                        fontSize = 11.sp,
                                        modifier = Modifier.padding(vertical = 6.dp)
                                    )
                                } else {
                                    moduleLessons.forEach { lesson ->
                                        val isCompleted = completedIds.contains(lesson.id)
                                        val isBookmarked = bookmarks.any { it.id == lesson.id }

                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = NavySurfaceVariant,
                                            border = androidx.compose.foundation.BorderStroke(
                                                0.5.dp,
                                                if (isCompleted) VitalGreen.copy(alpha = 0.5f) else BorderSubtle
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 4.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .clickable { onNavigateToLesson(lesson.id) }
                                                .testTag("lesson_item_${lesson.id}")
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(10.dp),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Column(modifier = Modifier.weight(1f)) {
                                                    Text(
                                                        text = lesson.title,
                                                        style = MaterialTheme.typography.bodyMedium,
                                                        fontWeight = FontWeight.SemiBold,
                                                        color = Color.White
                                                    )
                                                    Text(
                                                        text = "${lesson.estimatedMinutes} min • ${lesson.beginnerExplanation.take(60)}...",
                                                        style = MaterialTheme.typography.bodySmall,
                                                        color = TextSecondary,
                                                        fontSize = 10.5.sp,
                                                        maxLines = 1
                                                    )
                                                }

                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    IconButton(
                                                        onClick = {
                                                            viewModel.toggleBookmark(
                                                                id = lesson.id,
                                                                type = "lesson",
                                                                title = lesson.title,
                                                                subtitle = lesson.category.displayName
                                                            )
                                                        },
                                                        modifier = Modifier.size(28.dp).minimumInteractiveComponentSize()
                                                    ) {
                                                        Icon(
                                                            imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                                                            contentDescription = "Bookmark",
                                                            tint = if (isBookmarked) AlertAmber else TextMuted,
                                                            modifier = Modifier.size(16.dp)
                                                        )
                                                    }

                                                    IconButton(
                                                        onClick = { viewModel.toggleLessonCompleted(lesson.id) },
                                                        modifier = Modifier.size(28.dp).minimumInteractiveComponentSize()
                                                    ) {
                                                        Icon(
                                                            imageVector = if (isCompleted) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked,
                                                            contentDescription = "Toggle Complete",
                                                            tint = if (isCompleted) VitalGreen else TextMuted,
                                                            modifier = Modifier.size(18.dp)
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
