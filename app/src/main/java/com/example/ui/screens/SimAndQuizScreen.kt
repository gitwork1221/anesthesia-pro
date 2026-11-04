package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.data.model.ClinicalCase
import com.example.data.model.QuizQuestion
import com.example.ui.components.StatusBadge
import com.example.ui.theme.*
import com.example.ui.viewmodel.AnesthesiaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimAndQuizScreen(
    viewModel: AnesthesiaViewModel,
    onNavigateToCase: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTopTab by remember { mutableIntStateOf(0) } // 0: Case Simulator, 1: MCQ Quiz
    val cases = viewModel.getClinicalCases()
    val quizQuestions = viewModel.getQuizQuestions()
    val quizState by viewModel.quizState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DeepObsidian)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Clinical Simulations & Exam Prep",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "Interactive OR crisis decision branching & board review questions",
            style = MaterialTheme.typography.labelSmall,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(12.dp))

        SecondaryTabRow(
            selectedTabIndex = selectedTopTab,
            containerColor = NavySurface,
            contentColor = SurgicalCyan,
            divider = { HorizontalDivider(color = BorderSubtle) }
        ) {
            Tab(
                selected = selectedTopTab == 0,
                onClick = { selectedTopTab = 0 },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Filled.HealthAndSafety, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Crisis Sim (${cases.size})", style = MaterialTheme.typography.labelSmall)
                    }
                }
            )
            Tab(
                selected = selectedTopTab == 1,
                onClick = { selectedTopTab = 1 },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Filled.Quiz, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("MCQ Exam Practice (${quizQuestions.size})", style = MaterialTheme.typography.labelSmall)
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        if (selectedTopTab == 0) {
            // CRISIS SIMULATORS LIST
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(cases, key = { it.id }) { c ->
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = NavySurface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .clickable {
                                viewModel.startCaseSimulation(c.id)
                                onNavigateToCase(c.id)
                            }
                            .testTag("sim_case_${c.id}")
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                StatusBadge(status = c.category, color = AlarmRose)
                                Text(
                                    text = "${c.stages.size} Decision Stages",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = TextSecondary
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = c.title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = c.patientBrief,
                                style = MaterialTheme.typography.bodySmall,
                                color = TextSecondary,
                                maxLines = 2,
                                lineHeight = 16.sp
                            )

                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Vitals, Interventions & Debrief",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = TextMuted
                                )
                                Text(
                                    text = "Launch Scenario →",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = SurgicalCyan,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        } else {
            // MCQ PRACTICE QUIZ
            if (quizQuestions.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No questions found", color = TextSecondary)
                }
            } else if (quizState.isFinished) {
                // Quiz Finish Card
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = NavySurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SurgicalCyan),
                    modifier = Modifier.fillMaxWidth().testTag("quiz_finish_card")
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Filled.EmojiEvents,
                            contentDescription = null,
                            tint = AlertAmber,
                            modifier = Modifier.size(56.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Examination Practice Complete!",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Score: ${quizState.score} of ${quizQuestions.size} (${(quizState.score * 100 / quizQuestions.size)}%)",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = SurgicalCyan
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { viewModel.resetQuiz() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SurgicalCyan,
                                contentColor = DeepObsidian
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth().minimumInteractiveComponentSize()
                        ) {
                            Text("Retake Practice Questions")
                        }
                    }
                }
            } else {
                val q = quizQuestions[quizState.currentQuestionIndex]

                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = NavySurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                    modifier = Modifier.fillMaxWidth().testTag("quiz_question_card")
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            StatusBadge(status = q.topic, color = SurgicalCyan)
                            Text(
                                text = "Question ${quizState.currentQuestionIndex + 1} of ${quizQuestions.size}",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextSecondary
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = q.stem,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            lineHeight = 22.sp
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Options
                        q.options.forEachIndexed { index, option ->
                            val isSelected = quizState.selectedOptionIndex == index
                            val isCorrect = index == q.correctIndex
                            val showFeedback = quizState.isAnswerSubmitted

                            val cardColor = when {
                                showFeedback && isCorrect -> VitalGreen.copy(alpha = 0.2f)
                                showFeedback && isSelected && !isCorrect -> AlarmRose.copy(alpha = 0.2f)
                                isSelected -> SurgicalCyan.copy(alpha = 0.15f)
                                else -> NavySurfaceVariant
                            }

                            val borderColor = when {
                                showFeedback && isCorrect -> VitalGreen
                                showFeedback && isSelected && !isCorrect -> AlarmRose
                                isSelected -> SurgicalCyan
                                else -> BorderSubtle
                            }

                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = cardColor,
                                border = androidx.compose.foundation.BorderStroke(1.dp, borderColor),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable(enabled = !quizState.isAnswerSubmitted) {
                                        viewModel.submitQuizAnswer(index, q.correctIndex)
                                    }
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${('A'.code + index).toChar()}.",
                                        fontWeight = FontWeight.Bold,
                                        color = if (showFeedback && isCorrect) VitalGreen else SurgicalCyan,
                                        modifier = Modifier.width(24.dp)
                                    )
                                    Text(
                                        text = option,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = TextPrimary,
                                        modifier = Modifier.weight(1f)
                                    )
                                    if (showFeedback && isCorrect) {
                                        Icon(imageVector = Icons.Filled.CheckCircle, contentDescription = "Correct", tint = VitalGreen)
                                    } else if (showFeedback && isSelected && !isCorrect) {
                                        Icon(imageVector = Icons.Filled.Cancel, contentDescription = "Incorrect", tint = AlarmRose)
                                    }
                                }
                            }
                        }

                        // Explanation after submission
                        if (quizState.isAnswerSubmitted) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = NavySurfaceVariant,
                                border = androidx.compose.foundation.BorderStroke(0.5.dp, BorderSubtle),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = "Clinical Rationale & Evidence:",
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = SurgicalCyan
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = q.explanation,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = TextPrimary,
                                        lineHeight = 18.sp
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))
                            Button(
                                onClick = { viewModel.nextQuizQuestion(quizQuestions.size) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = SurgicalCyan,
                                    contentColor = DeepObsidian
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.fillMaxWidth().minimumInteractiveComponentSize().testTag("next_question_button")
                            ) {
                                Text(
                                    if (quizState.currentQuestionIndex + 1 >= quizQuestions.size) "Finish Exam" else "Next Question →",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
