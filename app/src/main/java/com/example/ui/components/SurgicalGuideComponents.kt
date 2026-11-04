package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.example.data.model.DrugReference
import com.example.data.model.ReviewQuestion
import com.example.data.model.SurgicalGuide
import com.example.ui.theme.*

@Composable
fun SurgicalSectionCard(
    title: String,
    icon: ImageVector,
    accentColor: Color,
    content: String?,
    modifier: Modifier = Modifier,
    testTag: String = ""
) {
    if (content.isNullOrBlank()) return
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = BorderStroke(1.dp, BorderSubtle),
        modifier = modifier
            .fillMaxWidth()
            .then(if (testTag.isNotEmpty()) Modifier.testTag(testTag) else Modifier)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = accentColor,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                color = TextPrimary,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
fun SurgicalBulletsCard(
    title: String,
    icon: ImageVector,
    accentColor: Color,
    bullets: List<String>,
    modifier: Modifier = Modifier,
    borderColor: Color = BorderSubtle,
    bulletColor: Color = accentColor,
    testTag: String = ""
) {
    if (bullets.isEmpty()) return
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = BorderStroke(1.dp, borderColor),
        modifier = modifier
            .fillMaxWidth()
            .then(if (testTag.isNotEmpty()) Modifier.testTag(testTag) else Modifier)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = accentColor,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            bullets.forEach { item ->
                Row(
                    modifier = Modifier.padding(vertical = 3.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text("• ", color = bulletColor, fontWeight = FontWeight.Bold)
                    Text(
                        text = item,
                        color = TextPrimary,
                        style = MaterialTheme.typography.bodySmall,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CrossModuleLinksCard(
    guide: SurgicalGuide,
    onNavigateToAnatomy: (String) -> Unit,
    onNavigateToDrug: (String) -> Unit,
    onNavigateToEquipment: (String) -> Unit,
    onNavigateToCalculator: () -> Unit,
    onNavigateToCase: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val hasLinks = guide.relatedAnatomyTopicIds.isNotEmpty() ||
            guide.relatedDrugIds.isNotEmpty() ||
            guide.relatedEquipmentIds.isNotEmpty() ||
            guide.relatedCalculatorIds.isNotEmpty() ||
            guide.relatedCaseId != null

    if (!hasLinks) return

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = BorderStroke(1.dp, SurgicalCyan.copy(alpha = 0.5f)),
        modifier = modifier
            .fillMaxWidth()
            .testTag("cross_module_links_card")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Hub,
                    contentDescription = null,
                    tint = SurgicalCyan,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Connected Clinical Modules",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            // Anatomy Links
            if (guide.relatedAnatomyTopicIds.isNotEmpty()) {
                Text(
                    text = "Applied Anatomy & Physiology",
                    style = MaterialTheme.typography.labelMedium,
                    color = ClinicalTeal,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    guide.relatedAnatomyTopicIds.forEach { topicId ->
                        val label = formatLinkTitle(topicId, "anatomy_")
                        AssistChip(
                            onClick = { onNavigateToAnatomy(topicId) },
                            label = { Text(label, style = MaterialTheme.typography.labelSmall) },
                            leadingIcon = {
                                Icon(Icons.Filled.Visibility, contentDescription = null, modifier = Modifier.size(14.dp))
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = DeepObsidian,
                                labelColor = SurgicalCyan,
                                leadingIconContentColor = SurgicalCyan
                            ),
                            border = BorderStroke(1.dp, BorderSubtle),
                            modifier = Modifier.minimumInteractiveComponentSize()
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            // Pharmacology Links
            if (guide.relatedDrugIds.isNotEmpty()) {
                Text(
                    text = "Pharmacology Monographs",
                    style = MaterialTheme.typography.labelMedium,
                    color = ElectricBlue,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    guide.relatedDrugIds.forEach { drugId ->
                        val label = formatLinkTitle(drugId, "drug_")
                        AssistChip(
                            onClick = { onNavigateToDrug(drugId) },
                            label = { Text(label, style = MaterialTheme.typography.labelSmall) },
                            leadingIcon = {
                                Icon(Icons.Filled.Medication, contentDescription = null, modifier = Modifier.size(14.dp))
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = DeepObsidian,
                                labelColor = ElectricBlue,
                                leadingIconContentColor = ElectricBlue
                            ),
                            border = BorderStroke(1.dp, BorderSubtle),
                            modifier = Modifier.minimumInteractiveComponentSize()
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            // Equipment & Calculators
            if (guide.relatedEquipmentIds.isNotEmpty() || guide.relatedCalculatorIds.isNotEmpty()) {
                Text(
                    text = "Equipment & Calculators",
                    style = MaterialTheme.typography.labelMedium,
                    color = AlertAmber,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    guide.relatedEquipmentIds.forEach { eqId ->
                        val label = formatLinkTitle(eqId, "equip_")
                        AssistChip(
                            onClick = { onNavigateToEquipment(eqId) },
                            label = { Text(label, style = MaterialTheme.typography.labelSmall) },
                            leadingIcon = {
                                Icon(Icons.Filled.PrecisionManufacturing, contentDescription = null, modifier = Modifier.size(14.dp))
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = DeepObsidian,
                                labelColor = AlertAmber,
                                leadingIconContentColor = AlertAmber
                            ),
                            border = BorderStroke(1.dp, BorderSubtle),
                            modifier = Modifier.minimumInteractiveComponentSize()
                        )
                    }
                    guide.relatedCalculatorIds.forEach { calcId ->
                        val label = formatLinkTitle(calcId, "calc_")
                        AssistChip(
                            onClick = { onNavigateToCalculator() },
                            label = { Text("Calc: $label", style = MaterialTheme.typography.labelSmall) },
                            leadingIcon = {
                                Icon(Icons.Filled.Calculate, contentDescription = null, modifier = Modifier.size(14.dp))
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = DeepObsidian,
                                labelColor = VitalGreen,
                                leadingIconContentColor = VitalGreen
                            ),
                            border = BorderStroke(1.dp, BorderSubtle),
                            modifier = Modifier.minimumInteractiveComponentSize()
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            // Case Simulation Link
            if (guide.relatedCaseId != null) {
                OutlinedButton(
                    onClick = { onNavigateToCase(guide.relatedCaseId) },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = AlarmRose
                    ),
                    border = BorderStroke(1.dp, AlarmRose),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("launch_related_sim_button")
                        .minimumInteractiveComponentSize()
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayCircle,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Launch Emergency Case Simulation",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun InteractiveGuideQuestionsCard(
    questions: List<ReviewQuestion>,
    modifier: Modifier = Modifier
) {
    if (questions.isEmpty()) return

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = BorderStroke(1.dp, ClinicalTeal.copy(alpha = 0.5f)),
        modifier = modifier
            .fillMaxWidth()
            .testTag("guide_case_questions_card")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Quiz,
                    contentDescription = null,
                    tint = ClinicalTeal,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Clinical Case Questions & Board Pearls",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            questions.forEachIndexed { qIndex, questionItem ->
                var selectedOption by remember { mutableStateOf<Int?>(null) }
                var showRationale by remember { mutableStateOf(false) }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DeepObsidian.copy(alpha = 0.6f), RoundedCornerShape(10.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text = "Question ${qIndex + 1} • ${questionItem.examHighYieldTag}",
                        style = MaterialTheme.typography.labelSmall,
                        color = SurgicalCyan,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = questionItem.question,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    questionItem.options.forEachIndexed { optIndex, optionText ->
                        val isSelected = selectedOption == optIndex
                        val isCorrect = optIndex == questionItem.correctIndex
                        val optionBg = when {
                            showRationale && isCorrect -> VitalGreen.copy(alpha = 0.2f)
                            showRationale && isSelected && !isCorrect -> AlarmRose.copy(alpha = 0.2f)
                            isSelected -> SurgicalCyan.copy(alpha = 0.15f)
                            else -> NavySurface
                        }
                        val optionBorder = when {
                            showRationale && isCorrect -> VitalGreen
                            showRationale && isSelected && !isCorrect -> AlarmRose
                            isSelected -> SurgicalCyan
                            else -> BorderSubtle
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(optionBg)
                                .border(1.dp, optionBorder, RoundedCornerShape(8.dp))
                                .clickable {
                                    if (!showRationale) {
                                        selectedOption = optIndex
                                    }
                                }
                                .padding(horizontal = 10.dp, vertical = 8.dp)
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = {
                                    if (!showRationale) selectedOption = optIndex
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = if (showRationale && isCorrect) VitalGreen else SurgicalCyan,
                                    unselectedColor = TextSecondary
                                ),
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = optionText,
                                style = MaterialTheme.typography.bodySmall,
                                color = if (showRationale && isCorrect) VitalGreen else TextPrimary,
                                fontWeight = if (showRationale && isCorrect) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    if (!showRationale) {
                        Button(
                            onClick = {
                                if (selectedOption != null) {
                                    showRationale = true
                                }
                            },
                            enabled = selectedOption != null,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = ClinicalTeal,
                                contentColor = DeepObsidian
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .align(Alignment.End)
                                .minimumInteractiveComponentSize()
                        ) {
                            Text("Check Rationale", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium)
                        }
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(NavySurface, RoundedCornerShape(8.dp))
                                .padding(10.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                val isRight = selectedOption == questionItem.correctIndex
                                Icon(
                                    imageVector = if (isRight) Icons.Filled.CheckCircle else Icons.Filled.Cancel,
                                    contentDescription = null,
                                    tint = if (isRight) VitalGreen else AlarmRose,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (isRight) "Correct Answer" else "Incorrect Selection",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isRight) VitalGreen else AlarmRose
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = questionItem.explanation,
                                style = MaterialTheme.typography.bodySmall,
                                color = TextPrimary,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
                if (qIndex < questions.size - 1) {
                    Spacer(modifier = Modifier.height(14.dp))
                }
            }
        }
    }
}

@Composable
fun GuideReferencesCard(
    references: List<DrugReference>,
    reviewStatus: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = BorderStroke(1.dp, BorderSubtle),
        modifier = modifier
            .fillMaxWidth()
            .testTag("guide_references_card")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.MenuBook,
                        contentDescription = null,
                        tint = TextSecondary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Clinical Governance & References",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                StatusBadge(status = reviewStatus, color = ClinicalTeal)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Evidence-based perioperative protocol • Peer reviewed standard",
                style = MaterialTheme.typography.labelSmall,
                color = TextMuted
            )
            if (references.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                references.forEach { ref ->
                    Row(
                        modifier = Modifier.padding(vertical = 2.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text("• ", color = ClinicalTeal, fontWeight = FontWeight.Bold)
                        Text(
                            text = "${ref.sourceTitle} (${ref.editionOrDate}) — ${ref.organizationOrJournal}",
                            color = TextSecondary,
                            style = MaterialTheme.typography.labelSmall,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }
    }
}

private fun formatLinkTitle(rawId: String, prefix: String): String {
    return rawId.removePrefix(prefix)
        .replace('_', ' ')
        .split(' ')
        .joinToString(" ") { word ->
            word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
}
