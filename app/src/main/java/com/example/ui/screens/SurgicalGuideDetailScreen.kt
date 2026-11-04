package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.*
import com.example.ui.theme.*
import com.example.ui.viewmodel.AnesthesiaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurgicalGuideDetailScreen(
    guideId: String,
    viewModel: AnesthesiaViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToAnatomy: (String) -> Unit = {},
    onNavigateToDrug: (String) -> Unit = {},
    onNavigateToEquipment: (String) -> Unit = {},
    onNavigateToCalculator: () -> Unit = {},
    onNavigateToCase: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val guide = viewModel.getSurgicalGuide(guideId)
    val bookmarks by viewModel.bookmarks.collectAsState()
    val isBookmarked = bookmarks.any { it.id == guideId }

    if (guide == null) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(DeepObsidian),
            contentAlignment = Alignment.Center
        ) {
            Text("Surgical guide not found", color = TextSecondary)
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = guide.procedureName,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = guide.surgicalSpecialty,
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier
                            .testTag("guide_back_button")
                            .minimumInteractiveComponentSize()
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = SurgicalCyan
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.toggleBookmark(
                                id = guide.id,
                                type = "guide",
                                title = guide.procedureName,
                                subtitle = guide.surgicalSpecialty
                            )
                        },
                        modifier = Modifier
                            .testTag("guide_bookmark_button")
                            .minimumInteractiveComponentSize()
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                            contentDescription = "Bookmark Guide",
                            tint = if (isBookmarked) AlertAmber else Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DeepObsidian)
            )
        },
        containerColor = DeepObsidian,
        modifier = modifier
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header Card: Overview & Specialty
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = NavySurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("guide_overview_card")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            StatusBadge(status = guide.surgicalSpecialty, color = SurgicalCyan)
                            StatusBadge(status = guide.reviewStatus, color = ClinicalTeal)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = guide.procedureName,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = guide.overview,
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextPrimary,
                            lineHeight = 20.sp
                        )
                    }
                }
            }

            // Cross-Module Quick Links (Anatomy, Drugs, Equipment, Calculators, Case Sim)
            item {
                CrossModuleLinksCard(
                    guide = guide,
                    onNavigateToAnatomy = onNavigateToAnatomy,
                    onNavigateToDrug = onNavigateToDrug,
                    onNavigateToEquipment = onNavigateToEquipment,
                    onNavigateToCalculator = onNavigateToCalculator,
                    onNavigateToCase = onNavigateToCase
                )
            }

            // Section 2: Surgical Position & Physiological Effects
            item {
                val positionText = guide.surgicalPositionAndPhysiology?.ifBlank { guide.positioningAndNerveSafety } ?: guide.positioningAndNerveSafety
                SurgicalSectionCard(
                    title = "Surgical Position & Physiological Effects",
                    icon = Icons.Filled.AccessibilityNew,
                    accentColor = AlertAmber,
                    content = positionText,
                    testTag = "section_positioning"
                )
            }

            // Section 3: Relevant Anatomy & Physiology
            item {
                SurgicalSectionCard(
                    title = "Relevant Anatomy & Physiology",
                    icon = Icons.Filled.Visibility,
                    accentColor = ClinicalTeal,
                    content = guide.relevantAnatomyPhysiology.orEmpty(),
                    testTag = "section_anatomy"
                )
            }

            // Section 4: Preoperative Assessment & Optimization
            item {
                SurgicalSectionCard(
                    title = "Preoperative Assessment & Optimization",
                    icon = Icons.Filled.AssignmentTurnedIn,
                    accentColor = ClinicalTeal,
                    content = guide.preoperativeAssessment,
                    testTag = "section_preop"
                )
            }

            // Section 5: Airway, Aspiration & Ventilation Considerations
            item {
                SurgicalSectionCard(
                    title = "Airway, Aspiration & Ventilation",
                    icon = Icons.Filled.Air,
                    accentColor = ElectricBlue,
                    content = guide.airwayConsiderations,
                    testTag = "section_airway"
                )
            }

            // Section 6: Anesthetic Technique Options & Trade-Offs
            item {
                SurgicalSectionCard(
                    title = "Anesthetic Technique & Dosing",
                    icon = Icons.Filled.Vaccines,
                    accentColor = SurgicalCyan,
                    content = guide.anestheticTechnique,
                    testTag = "section_technique"
                )
            }

            // Section 7: Monitoring & Vascular Access Considerations
            item {
                SurgicalSectionCard(
                    title = "Monitoring & Vascular Access",
                    icon = Icons.Filled.Speed,
                    accentColor = VitalGreen,
                    content = guide.monitoringAndAccess.orEmpty(),
                    testTag = "section_monitoring"
                )
            }

            // Section 8: Drug & Fluid Strategy
            item {
                val fluidText = guide.drugAndFluidStrategy?.ifBlank { guide.hemodynamicManagement } ?: guide.hemodynamicManagement
                SurgicalSectionCard(
                    title = "Drug & Fluid Strategy",
                    icon = Icons.Filled.Medication,
                    accentColor = ElectricBlue,
                    content = fluidText,
                    testTag = "section_fluid_drugs"
                )
            }

            // Section 9: Expected Blood Loss & Transfusion Planning
            item {
                SurgicalSectionCard(
                    title = "Expected Blood Loss & Transfusion Planning",
                    icon = Icons.Filled.Bloodtype,
                    accentColor = AlarmRose,
                    content = guide.expectedBloodLossAndTransfusion.orEmpty(),
                    testTag = "section_blood_loss"
                )
            }

            // Section 10: Procedure-Specific Complications & Principles
            item {
                SurgicalBulletsCard(
                    title = "Intraoperative Complications & Response Principles",
                    icon = Icons.Filled.Warning,
                    accentColor = AlarmRose,
                    bullets = guide.potentialComplications,
                    borderColor = AlarmRose.copy(alpha = 0.5f),
                    bulletColor = AlarmRose,
                    testTag = "section_complications"
                )
            }

            // Section 11: Emergence & Multimodal Analgesia Strategy
            item {
                SurgicalSectionCard(
                    title = "Multimodal Analgesia Strategy",
                    icon = Icons.Filled.Healing,
                    accentColor = SurgicalCyan,
                    content = guide.analgesiaPlan,
                    testTag = "section_analgesia"
                )
            }

            // Section 12: Postoperative Monitoring & Disposition
            item {
                SurgicalSectionCard(
                    title = "Postoperative Recovery & Disposition",
                    icon = Icons.Filled.Bed,
                    accentColor = ClinicalTeal,
                    content = guide.postoperativeRecovery,
                    testTag = "section_recovery"
                )
            }

            // Section 13: High-Yield Exam Points & Clinical Misconceptions
            item {
                SurgicalBulletsCard(
                    title = "High-Yield Exam Points & Misconceptions",
                    icon = Icons.Filled.School,
                    accentColor = AlertAmber,
                    bullets = guide.highYieldExamPoints,
                    borderColor = AlertAmber.copy(alpha = 0.5f),
                    bulletColor = AlertAmber,
                    testTag = "section_high_yield"
                )
            }

            // Section 14: Case-Based Questions & Board Pearls
            item {
                InteractiveGuideQuestionsCard(questions = guide.caseQuestions)
            }

            // Clinical References & Review Governance
            item {
                GuideReferencesCard(
                    references = guide.references,
                    reviewStatus = guide.reviewStatus
                )
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
