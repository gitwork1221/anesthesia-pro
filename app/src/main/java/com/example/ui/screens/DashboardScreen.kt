package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.AcademicYear
import com.example.data.model.CurriculumMappingStatus
import com.example.ui.components.MedicalSectionHeader
import com.example.ui.components.OfflineNoticeCard
import com.example.ui.components.StatusBadge
import com.example.ui.theme.*
import com.example.ui.viewmodel.AnesthesiaViewModel
import com.example.ui.viewmodel.NavigationDestination

@Composable
fun DashboardScreen(
    viewModel: AnesthesiaViewModel,
    onNavigateToLesson: (String) -> Unit,
    onNavigateToCase: (String) -> Unit,
    onNavigateToAnatomy: () -> Unit,
    onOpenPathwayDialog: () -> Unit,
    onNavigateToSettings: () -> Unit = { viewModel.navigateTo(NavigationDestination.Settings) },
    modifier: Modifier = Modifier
) {
    val pathway by viewModel.pathway.collectAsState()
    val studyLevel by viewModel.studyLevel.collectAsState()
    val selectedYear by viewModel.selectedAcademicYear.collectAsState()
    val streakDays by viewModel.studyStreakDays.collectAsState()
    val completedIds by viewModel.completedLessonIds.collectAsState()
    val allLessons = viewModel.getLessons()
    val lastOpenedLesson = viewModel.getLastOpenedLesson() ?: allLessons.firstOrNull()
    val overallProgress = viewModel.getOverallProgressFraction()
    val allModules = viewModel.getAllModules()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DeepObsidian),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // ==========================================
        // SECTION A: HEADER & PATHWAY BRANDING
        // ==========================================
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = NavySurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("dashboard_header_card")
            ) {
                Box(modifier = Modifier.fillMaxWidth().height(170.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.hero_anesthesia),
                        contentDescription = "Operating Room Workstation",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, DeepObsidian.copy(alpha = 0.94f)),
                                    startY = 40f
                                )
                            )
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(14.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                StatusBadge(
                                    status = "ETHIOPIAN BSc CURRICULUM",
                                    color = SurgicalCyan
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                StatusBadge(status = studyLevel.displayName.take(12), color = ClinicalTeal)
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(
                                    onClick = onOpenPathwayDialog,
                                    modifier = Modifier.size(32.dp).minimumInteractiveComponentSize().testTag("header_pathway_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Tune,
                                        contentDescription = "Curriculum Settings",
                                        tint = SurgicalCyan,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                IconButton(
                                    onClick = onNavigateToSettings,
                                    modifier = Modifier.size(32.dp).minimumInteractiveComponentSize().testTag("header_settings_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Settings,
                                        contentDescription = "Settings",
                                        tint = SurgicalCyan,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "ANESTHESIA PRO",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "National Harmonized Curriculum for Generic BSc in Anesthesia (Feb 2021)",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary,
                            maxLines = 1
                        )
                    }
                }
            }
        }

        // ==========================================
        // SECTION B: LEARNING PROGRESS
        // ==========================================
        item {
            MedicalSectionHeader(
                title = "Learning Progress",
                subtitle = "Active degree track completion records",
                icon = Icons.Filled.TrendingUp
            )

            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = NavySurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("dashboard_progress_card")
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Degree Progress (${(overallProgress * 100).toInt()}%)",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "${completedIds.size} of ${allLessons.size} core lessons completed",
                                style = MaterialTheme.typography.bodySmall,
                                color = TextSecondary
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(if (streakDays > 0) AlertAmber.copy(alpha = 0.15f) else NavySurfaceVariant),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.LocalFireDepartment,
                                    contentDescription = "Streak",
                                    tint = if (streakDays > 0) AlertAmber else TextMuted,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "$streakDays d streak",
                                style = MaterialTheme.typography.labelSmall,
                                color = if (streakDays > 0) AlertAmber else TextMuted,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    LinearProgressIndicator(
                        progress = { overallProgress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = SurgicalCyan,
                        trackColor = BorderSubtle
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Continue learning button / Empty State
                    if (completedIds.isEmpty() && viewModel.lastOpenedLessonId.collectAsState().value == null) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = NavySurfaceVariant.copy(alpha = 0.6f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.School,
                                    contentDescription = null,
                                    tint = SurgicalCyan,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "New Learner — Ready to Begin",
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Start with Year I biomedical foundations or airway assessment.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = TextSecondary,
                                        fontSize = 11.sp
                                    )
                                }
                                if (lastOpenedLesson != null) {
                                    Button(
                                        onClick = { onNavigateToLesson(lastOpenedLesson.id) },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = SurgicalCyan, contentColor = DeepObsidian),
                                        modifier = Modifier.minimumInteractiveComponentSize().testTag("start_first_lesson_button")
                                    ) {
                                        Text("Start", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    } else if (lastOpenedLesson != null) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = NavySurfaceVariant,
                            border = androidx.compose.foundation.BorderStroke(1.dp, SurgicalCyan.copy(alpha = 0.4f)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { onNavigateToLesson(lastOpenedLesson.id) }
                                .testTag("continue_learning_button")
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.PlayCircle,
                                        contentDescription = null,
                                        tint = SurgicalCyan,
                                        modifier = Modifier.size(28.dp)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            text = "Continue: ${lastOpenedLesson.title}",
                                            style = MaterialTheme.typography.titleSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            maxLines = 1
                                        )
                                        Text(
                                            text = "${lastOpenedLesson.category.displayName} • ${lastOpenedLesson.estimatedMinutes} min",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = TextSecondary,
                                            fontSize = 11.sp
                                        )
                                    }
                                }
                                Icon(
                                    imageVector = Icons.Filled.ArrowForward,
                                    contentDescription = "Resume Lesson",
                                    tint = SurgicalCyan,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // ==========================================
        // SECTION C: ETHIOPIAN BSc CURRICULUM ENTRY
        // ==========================================
        item {
            MedicalSectionHeader(
                title = "Ethiopian BSc Curriculum",
                subtitle = "Four-year generic modular syllabus (Feb 2021)",
                icon = Icons.Filled.AccountTree,
                actionText = "Open Syllabus",
                onActionClick = { viewModel.navigateTo(NavigationDestination.Curriculum) }
            )

            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = NavySurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("ethiopian_curriculum_primary_card")
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        StatusBadge(
                            status = "4 Academic Years • 10 Core Modules",
                            color = SurgicalCyan
                        )
                        Text(
                            text = "Feb 2021 Framework",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextMuted
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "National Modular Structure",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Curriculum modules organized sequentially from Year I foundational biomedical sciences through Year IV senior clinical internship.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Year summary chips
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        AcademicYear.entries.forEach { yr ->
                            val moduleCount = viewModel.getModulesByYear(yr).size
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (selectedYear == yr) SurgicalCyan.copy(alpha = 0.2f) else NavySurfaceVariant,
                                border = androidx.compose.foundation.BorderStroke(
                                    1.dp,
                                    if (selectedYear == yr) SurgicalCyan else BorderSubtle
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        viewModel.selectedAcademicYear.value = yr
                                        viewModel.navigateTo(NavigationDestination.Curriculum)
                                    }
                            ) {
                                Column(
                                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = yr.romanNumeral,
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = if (selectedYear == yr) SurgicalCyan else Color.White
                                    )
                                    Text(
                                        text = "$moduleCount mods",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = TextMuted,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null,
                            tint = ClinicalTeal,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Module codes & ECTS awaiting official primary syllabus verification.",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextMuted,
                            fontSize = 10.5.sp
                        )
                    }
                }
            }
        }

        // ==========================================
        // SECTION D: CORE LEARNING AREAS (8 Cards)
        // ==========================================
        item {
            MedicalSectionHeader(
                title = "Core Learning Areas",
                subtitle = "8 integrated learning and assessment modules",
                icon = Icons.Filled.GridView
            )
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                // Row 1: Curriculum & Anatomy
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    CoreAreaCard(
                        title = "Curriculum Browser",
                        subtitle = "Years I-IV syllabus, modules & competencies",
                        icon = Icons.Filled.AccountTree,
                        accentColor = SurgicalCyan,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(NavigationDestination.Curriculum) }
                    )
                    CoreAreaCard(
                        title = "Applied Anatomy",
                        subtitle = "Airway, neuraxial & cardiovascular landmarks",
                        icon = Icons.Filled.Biotech,
                        accentColor = ClinicalTeal,
                        modifier = Modifier.weight(1f),
                        onClick = onNavigateToAnatomy
                    )
                }

                // Row 2: Pharmacology & Surgical Guides
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    CoreAreaCard(
                        title = "Pharmacology",
                        subtitle = "Volatiles, IV anesthetics, blocks & reversals",
                        icon = Icons.Filled.Medication,
                        accentColor = SurgicalCyan,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(NavigationDestination.Pharmacology) }
                    )
                    CoreAreaCard(
                        title = "Surgical Guides",
                        subtitle = "General, OB/GYN, trauma & ortho protocols",
                        icon = Icons.Filled.MedicalServices,
                        accentColor = ClinicalTeal,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(NavigationDestination.Pharmacology) }
                    )
                }

                // Row 3: Equipment & Calculators
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    CoreAreaCard(
                        title = "Equipment Safety",
                        subtitle = "Workstation circles, vaporizers & alarms",
                        icon = Icons.Filled.PrecisionManufacturing,
                        accentColor = SurgicalCyan,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(NavigationDestination.Pharmacology) }
                    )
                    CoreAreaCard(
                        title = "Calculators",
                        subtitle = "BMI/IBW, pediatric ETT & LAST limits",
                        icon = Icons.Filled.Calculate,
                        accentColor = ClinicalTeal,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(NavigationDestination.Calculators) }
                    )
                }

                // Row 4: Simulation & MCQs
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    CoreAreaCard(
                        title = "Crisis Simulator",
                        subtitle = "Post-spinal hypotension & CICO scenarios",
                        icon = Icons.Filled.WarningAmber,
                        accentColor = AlarmRose,
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigateToCase("case_post_spinal_hypotension") }
                    )
                    CoreAreaCard(
                        title = "Exam MCQs",
                        subtitle = "Self-assessment with detailed rationales",
                        icon = Icons.Filled.Quiz,
                        accentColor = VitalGreen,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(NavigationDestination.SimAndQuiz) }
                    )
                }
            }
        }

        // ==========================================
        // SECTION E: SUPPLEMENTARY LEARNING (Segregated)
        // ==========================================
        item {
            MedicalSectionHeader(
                title = "Supplementary Learning",
                subtitle = "International reference guidelines (ASA, ASRA, MHAUS, DAS)",
                icon = Icons.Filled.MenuBook
            )

            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = NavySurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("supplementary_learning_card")
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        StatusBadge(status = "SUPPLEMENTARY REFERENCE", color = TextSecondary)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Non-Ethiopian Standards",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextMuted
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "International Professional Standards & Guidelines",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "These international society guidelines (ASA physical status, Difficult Airway Society algorithms, ASRA regional anesthesia guidelines, and Malignant Hyperthermia Association protocols) serve as evidence-based supplementary references alongside the Ethiopian national curriculum.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary,
                        fontSize = 11.5.sp,
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("ASA Physical Status", "DAS Airway Algorithm", "ASRA Neuraxial").forEach { tag ->
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = NavySurfaceVariant,
                                border = androidx.compose.foundation.BorderStroke(0.5.dp, BorderSubtle),
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { viewModel.navigateTo(NavigationDestination.Pharmacology) }
                            ) {
                                Text(
                                    text = tag,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = SurgicalCyan,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        }

        // Offline Notice & Medical Notice
        item {
            OfflineNoticeCard()
        }

        item {
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = NavySurface.copy(alpha = 0.5f),
                border = androidx.compose.foundation.BorderStroke(0.5.dp, BorderSubtle),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "Ethiopian Academic & Clinical Notice",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Anesthesia Pro is designed for Ethiopian Generic BSc in Anesthesia students and certified practitioners. While adhering to standard curricular competencies, this tool is educational and does NOT replace institutional protocols or supervising clinical judgment.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted,
                        fontSize = 11.sp,
                        lineHeight = 15.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun CoreAreaCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    accentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = NavySurface,
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = modifier
            .height(118.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(accentColor.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = accentColor,
                    modifier = Modifier.size(18.dp)
                )
            }
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 13.sp,
                    maxLines = 1
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary,
                    fontSize = 10.5.sp,
                    lineHeight = 14.sp,
                    maxLines = 2
                )
            }
        }
    }
}
