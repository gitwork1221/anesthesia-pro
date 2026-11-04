package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.StatusBadge
import com.example.ui.theme.*
import com.example.ui.viewmodel.AnesthesiaViewModel
import com.example.ui.viewmodel.NavigationDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonDetailScreen(
    lessonId: String,
    viewModel: AnesthesiaViewModel,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val lesson = viewModel.getLesson(lessonId)
    val textScale by viewModel.textScale.collectAsState()
    val completedIds by viewModel.completedLessonIds.collectAsState()
    val isCompleted = completedIds.contains(lessonId)
    val bookmarks by viewModel.bookmarks.collectAsState()
    val isBookmarked = bookmarks.any { it.id == lessonId }
    val allNotes by viewModel.allNotes.collectAsState()
    val lessonNotes = allNotes.filter { it.targetId == lessonId }

    // Automatically record this lesson as the last opened lesson
    LaunchedEffect(lessonId) {
        viewModel.recordLessonOpened(lessonId)
    }

    var selectedTab by remember { mutableIntStateOf(0) }
    var newNoteText by remember { mutableStateOf("") }
    var isAddingNote by remember { mutableStateOf(false) }

    if (lesson == null) {
        Box(
            modifier = modifier.fillMaxSize().background(DeepObsidian),
            contentAlignment = Alignment.Center
        ) {
            Text("Lesson not found", color = TextSecondary)
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = lesson.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack, modifier = Modifier.minimumInteractiveComponentSize()) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = SurgicalCyan
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { viewModel.cycleTextScale() },
                        modifier = Modifier.minimumInteractiveComponentSize()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.FormatSize,
                            contentDescription = "Adjust Font Size",
                            tint = SurgicalCyan
                        )
                    }

                    IconButton(
                        onClick = {
                            viewModel.toggleBookmark(
                                id = lesson.id,
                                type = "lesson",
                                title = lesson.title,
                                subtitle = lesson.category.displayName
                            )
                        },
                        modifier = Modifier.minimumInteractiveComponentSize()
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked) AlertAmber else Color.White
                        )
                    }

                    IconButton(
                        onClick = { viewModel.toggleLessonCompleted(lesson.id) },
                        modifier = Modifier.minimumInteractiveComponentSize()
                    ) {
                        Icon(
                            imageVector = if (isCompleted) Icons.Filled.CheckCircle else Icons.Filled.CheckCircleOutline,
                            contentDescription = "Toggle Complete",
                            tint = if (isCompleted) VitalGreen else Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DeepObsidian)
            )
        },
        containerColor = DeepObsidian,
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = NavySurface,
                contentColor = SurgicalCyan,
                divider = { HorizontalDivider(color = BorderSubtle) }
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = {
                        Text(
                            "Curriculum Unit",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = {
                        Text(
                            "Study Notes (${lessonNotes.size})",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }

            if (selectedTab == 0) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // 1. Header Card with Academic Year & Status
                    item {
                        Card(
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = NavySurface),
                            border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                            modifier = Modifier.fillMaxWidth().testTag("lesson_header_card")
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    StatusBadge(status = "${lesson.academicYear.romanNumeral} • ${lesson.category.displayName}", color = SurgicalCyan)
                                    StatusBadge(status = lesson.reviewStatus, color = ClinicalTeal)
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = lesson.title,
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = (18 * textScale).sp
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Target Scope: ${lesson.pathwayApplicability} • ${lesson.estimatedMinutes} min read",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = TextSecondary
                                )
                            }
                        }
                    }

                    // 2. Learning Objectives
                    item {
                        LessonSectionCard(
                            title = "1. Learning Objectives",
                            icon = Icons.Filled.TrackChanges,
                            accentColor = SurgicalCyan
                        ) {
                            lesson.learningObjectives.forEachIndexed { i, obj ->
                                Row(
                                    modifier = Modifier.padding(vertical = 3.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Text(
                                        text = "${i + 1}.",
                                        fontWeight = FontWeight.Bold,
                                        color = SurgicalCyan,
                                        fontSize = (14 * textScale).sp,
                                        modifier = Modifier.width(22.dp)
                                    )
                                    Text(
                                        text = obj,
                                        color = TextPrimary,
                                        fontSize = (14 * textScale).sp,
                                        lineHeight = (20 * textScale).sp
                                    )
                                }
                            }
                        }
                    }

                    // 3. Prerequisites
                    if (lesson.prerequisites.isNotEmpty()) {
                        item {
                            LessonSectionCard(
                                title = "2. Prerequisites & Foundational Knowledge",
                                icon = Icons.Filled.School,
                                accentColor = ClinicalTeal
                            ) {
                                lesson.prerequisites.forEach { req ->
                                    Row(
                                        modifier = Modifier.padding(vertical = 2.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Check,
                                            contentDescription = null,
                                            tint = ClinicalTeal,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = req,
                                            color = TextSecondary,
                                            fontSize = (13 * textScale).sp
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // 4. Intuitive Overview
                    item {
                        LessonSectionCard(
                            title = "3. Intuitive Overview",
                            icon = Icons.Filled.Lightbulb,
                            accentColor = AlertAmber
                        ) {
                            Text(
                                text = lesson.beginnerExplanation,
                                color = TextPrimary,
                                fontSize = (14 * textScale).sp,
                                lineHeight = (21 * textScale).sp
                            )
                        }
                    }

                    // 5. In-Depth Physiological Mechanisms
                    item {
                        LessonSectionCard(
                            title = "4. Physiological Mechanisms & Physics",
                            icon = Icons.Filled.MenuBook,
                            accentColor = SurgicalCyan
                        ) {
                            Text(
                                text = lesson.physiologicalMechanisms,
                                color = TextPrimary,
                                fontSize = (14 * textScale).sp,
                                lineHeight = (22 * textScale).sp
                            )
                        }
                    }

                    // 6. Clinical Applications & Practice
                    if (lesson.clinicalApplications.isNotBlank()) {
                        item {
                            LessonSectionCard(
                                title = "5. Clinical Applications & Dosing Strategy",
                                icon = Icons.Filled.Medication,
                                accentColor = ClinicalTeal
                            ) {
                                Text(
                                    text = lesson.clinicalApplications,
                                    color = TextPrimary,
                                    fontSize = (14 * textScale).sp,
                                    lineHeight = (22 * textScale).sp
                                )
                            }
                        }
                    }

                    // 7. High-Yield Key Points
                    item {
                        LessonSectionCard(
                            title = "6. High-Yield Board & Exam Distinctions",
                            icon = Icons.Filled.Star,
                            accentColor = AlertAmber
                        ) {
                            lesson.highYieldExamPoints.forEach { pt ->
                                Row(
                                    modifier = Modifier.padding(vertical = 3.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowRight,
                                        contentDescription = null,
                                        tint = AlertAmber,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = pt,
                                        color = TextPrimary,
                                        fontSize = (13.5 * textScale).sp,
                                        lineHeight = (19 * textScale).sp
                                    )
                                }
                            }
                        }
                    }

                    // 8. Clinical Case Study
                    if (lesson.clinicalCaseStudy.isNotBlank()) {
                        item {
                            LessonSectionCard(
                                title = "7. Clinical Case Scenario",
                                icon = Icons.Filled.LocalHospital,
                                accentColor = VitalGreen
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = NavySurfaceVariant,
                                    border = androidx.compose.foundation.BorderStroke(0.5.dp, BorderSubtle),
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                                ) {
                                    Text(
                                        text = lesson.clinicalCaseStudy,
                                        color = TextPrimary,
                                        fontSize = (13 * textScale).sp,
                                        lineHeight = (19 * textScale).sp,
                                        modifier = Modifier.padding(10.dp)
                                    )
                                }
                            }
                        }
                    }

                    // 9. Common Errors & Pitfalls
                    if (lesson.commonErrors.isNotEmpty()) {
                        item {
                            LessonSectionCard(
                                title = "8. Common Errors & Clinical Pitfalls",
                                icon = Icons.Filled.WarningAmber,
                                accentColor = AlarmRose
                            ) {
                                lesson.commonErrors.forEach { err ->
                                    Row(
                                        modifier = Modifier.padding(vertical = 3.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Close,
                                            contentDescription = null,
                                            tint = AlarmRose,
                                            modifier = Modifier.size(16.dp).padding(top = 2.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = err,
                                            color = TextSecondary,
                                            fontSize = (13 * textScale).sp,
                                            lineHeight = (19 * textScale).sp
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // 9. Interactive Knowledge Check Question
                    if (lesson.knowledgeCheckQuestion != null) {
                        item {
                            InteractiveKnowledgeCheckCard(
                                question = lesson.knowledgeCheckQuestion
                            )
                        }
                    }

                    // 10. References & Literature
                    item {
                        Card(
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = NavySurface),
                            border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = "Authoritative Evidence & Literature",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = TextSecondary
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                lesson.references.forEach { ref ->
                                    Text(
                                        text = "• $ref",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = TextMuted,
                                        fontSize = 11.5.sp,
                                        modifier = Modifier.padding(vertical = 2.dp)
                                    )
                                }
                            }
                        }
                    }

                    // 11. Mark Complete Button
                    item {
                        Button(
                            onClick = { viewModel.toggleLessonCompleted(lesson.id) },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isCompleted) VitalGreen else SurgicalCyan,
                                contentColor = DeepObsidian
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("lesson_mark_completed_button")
                        ) {
                            Icon(
                                imageVector = if (isCompleted) Icons.Filled.CheckCircle else Icons.Filled.Check,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isCompleted) "Lesson Completed (Click to Unmark)" else "Mark Lesson as Complete",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            } else {
                // Study Notes Tab
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                ) {
                    if (isAddingNote) {
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = NavySurface),
                            border = androidx.compose.foundation.BorderStroke(1.dp, SurgicalCyan.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = "Add Clinical Study Note",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = newNoteText,
                                    onValueChange = { newNoteText = it },
                                    placeholder = { Text("Write key insights, drug dosages, or exam reminders...", color = TextMuted) },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = SurgicalCyan,
                                        unfocusedBorderColor = BorderSubtle,
                                        focusedTextColor = TextPrimary,
                                        unfocusedTextColor = TextPrimary
                                    ),
                                    modifier = Modifier.fillMaxWidth().height(120.dp),
                                    maxLines = 5
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    TextButton(onClick = { isAddingNote = false }) {
                                        Text("Cancel", color = TextSecondary)
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Button(
                                        onClick = {
                                            if (newNoteText.isNotBlank()) {
                                                viewModel.addNote(
                                                    targetId = lesson.id,
                                                    targetType = "lesson",
                                                    targetTitle = lesson.title,
                                                    content = newNoteText.trim()
                                                )
                                                newNoteText = ""
                                                isAddingNote = false
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = SurgicalCyan,
                                            contentColor = DeepObsidian
                                        )
                                    ) {
                                        Text("Save Note")
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    } else {
                        Button(
                            onClick = { isAddingNote = true },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = NavySurfaceVariant,
                                contentColor = SurgicalCyan
                            ),
                            modifier = Modifier.fillMaxWidth().minimumInteractiveComponentSize()
                        ) {
                            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Add New Clinical Note")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    if (lessonNotes.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Filled.EditNote,
                                    contentDescription = null,
                                    tint = TextMuted,
                                    modifier = Modifier.size(48.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "No study notes saved for this lesson yet.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = TextSecondary
                                )
                                Text(
                                    text = "Tap above to add personalized reminders or pearls.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextMuted
                                )
                            }
                        }
                    } else {
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            items(lessonNotes, key = { it.id }) { note ->
                                Card(
                                    shape = RoundedCornerShape(10.dp),
                                    colors = CardDefaults.cardColors(containerColor = NavySurface),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.padding(14.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Text(
                                            text = note.content,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = TextPrimary,
                                            modifier = Modifier.weight(1f)
                                        )
                                        IconButton(
                                            onClick = { viewModel.deleteNote(note.id) },
                                            modifier = Modifier.size(28.dp).minimumInteractiveComponentSize()
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.DeleteOutline,
                                                contentDescription = "Delete Note",
                                                tint = AlarmRose.copy(alpha = 0.8f),
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
