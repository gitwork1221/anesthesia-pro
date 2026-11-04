package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.StatusBadge
import com.example.ui.theme.*
import com.example.ui.viewmodel.AnesthesiaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaseSimDetailScreen(
    caseId: String,
    viewModel: AnesthesiaViewModel,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val simCase = viewModel.getClinicalCase(caseId)
    val simState by viewModel.caseSimState.collectAsState()

    if (simCase == null) {
        Box(modifier = modifier.fillMaxSize().background(DeepObsidian), contentAlignment = Alignment.Center) {
            Text("Simulation case not found", color = TextSecondary)
        }
        return
    }

    val totalStages = simCase.stages.size
    val currentStage = if (simState.currentStageIndex < totalStages) simCase.stages[simState.currentStageIndex] else null

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = simCase.title,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Interactive Crisis Management",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack, modifier = Modifier.minimumInteractiveComponentSize()) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = SurgicalCyan)
                    }
                },
                actions = {
                    IconButton(
                        onClick = { viewModel.restartCaseSimulation() },
                        modifier = Modifier.minimumInteractiveComponentSize()
                    ) {
                        Icon(imageVector = Icons.Filled.Refresh, contentDescription = "Restart Case", tint = SurgicalCyan)
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
            if (simState.isCompleted || currentStage == null) {
                // Completed Debriefing Screen
                item {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = NavySurface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, VitalGreen),
                        modifier = Modifier.fillMaxWidth().testTag("sim_completed_card")
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = null,
                                    tint = VitalGreen,
                                    modifier = Modifier.size(32.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "Simulation Concluded",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Performance: ${simState.stagesPassedCount} of $totalStages correct initial choices",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = ClinicalTeal
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = "Comprehensive Clinical Debrief:",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = SurgicalCyan
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = simCase.debriefSummary,
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextPrimary,
                                lineHeight = 22.sp
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = { viewModel.restartCaseSimulation() },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = SurgicalCyan,
                                    contentColor = DeepObsidian
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.fillMaxWidth().minimumInteractiveComponentSize()
                            ) {
                                Text("Replay Crisis Scenario", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            } else {
                // Patient Case Brief Card
                item {
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = NavySurface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                StatusBadge(status = simCase.category, color = AlarmRose)
                                Text(
                                    text = "Stage ${simState.currentStageIndex + 1} of $totalStages",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = SurgicalCyan,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = simCase.patientBrief,
                                style = MaterialTheme.typography.bodySmall,
                                color = TextSecondary,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }

                // Operating Room Telemetry Monitor (Digital Vitals)
                item {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFF040711),
                        border = androidx.compose.foundation.BorderStroke(1.5.dp, SurgicalCyan.copy(alpha = 0.6f)),
                        modifier = Modifier.fillMaxWidth().testTag("vitals_monitor")
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .clip(androidx.compose.foundation.shape.CircleShape)
                                            .background(VitalGreen)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "OR VITALS MONITOR",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = VitalGreen
                                    )
                                }
                                Text(
                                    text = "LEAD II • ECG 25 mm/s",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = TextMuted,
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 10.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = currentStage.vitals,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = SurgicalCyan,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }

                // Scenario Situation
                item {
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = NavySurface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                        modifier = Modifier.fillMaxWidth().testTag("scenario_card")
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = currentStage.stageTitle,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = currentStage.scenario,
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextPrimary,
                                lineHeight = 20.sp
                            )

                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = "What is your immediate clinical management action?",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = SurgicalCyan
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            // Interactive Options
                            currentStage.options.forEachIndexed { optIndex, optionText ->
                                val isSelected = simState.selectedOptionIndex == optIndex
                                val isCorrect = optIndex == currentStage.correctIndex
                                val answered = simState.hasAnsweredCurrentStage

                                val bgColor = when {
                                    answered && isCorrect -> VitalGreen.copy(alpha = 0.2f)
                                    answered && isSelected && !isCorrect -> AlarmRose.copy(alpha = 0.2f)
                                    isSelected -> SurgicalCyan.copy(alpha = 0.15f)
                                    else -> NavySurfaceVariant
                                }

                                val bdrColor = when {
                                    answered && isCorrect -> VitalGreen
                                    answered && isSelected && !isCorrect -> AlarmRose
                                    isSelected -> SurgicalCyan
                                    else -> BorderSubtle
                                }

                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = bgColor,
                                    border = androidx.compose.foundation.BorderStroke(1.dp, bdrColor),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .clickable(enabled = !answered) {
                                            viewModel.submitCaseStageOption(optIndex, isCorrect)
                                        }
                                ) {
                                    Row(
                                        modifier = Modifier.padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "${optIndex + 1}.",
                                            fontWeight = FontWeight.Bold,
                                            color = if (answered && isCorrect) VitalGreen else SurgicalCyan,
                                            modifier = Modifier.width(22.dp)
                                        )
                                        Text(
                                            text = optionText,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = TextPrimary,
                                            modifier = Modifier.weight(1f)
                                        )
                                        if (answered && isCorrect) {
                                            Icon(imageVector = Icons.Filled.CheckCircle, contentDescription = "Correct", tint = VitalGreen)
                                        } else if (answered && isSelected && !isCorrect) {
                                            Icon(imageVector = Icons.Filled.Cancel, contentDescription = "Incorrect", tint = AlarmRose)
                                        }
                                    }
                                }
                            }

                            // Feedback after selection
                            if (simState.hasAnsweredCurrentStage) {
                                Spacer(modifier = Modifier.height(12.dp))
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = NavySurfaceVariant,
                                    border = androidx.compose.foundation.BorderStroke(0.5.dp, BorderSubtle),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Text(
                                            text = currentStage.immediateFeedback,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = TextPrimary,
                                            lineHeight = 18.sp
                                        )
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = "Clinical Rationale: ${currentStage.rationale}",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = TextSecondary,
                                            lineHeight = 16.sp
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(14.dp))
                                Button(
                                    onClick = { viewModel.proceedToNextCaseStage(totalStages) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = SurgicalCyan,
                                        contentColor = DeepObsidian
                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.fillMaxWidth().minimumInteractiveComponentSize().testTag("proceed_stage_button")
                                ) {
                                    Text(
                                        if (simState.currentStageIndex + 1 >= totalStages) "Finish & View Debrief →" else "Advance to Next Stage →",
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
