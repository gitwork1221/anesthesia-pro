package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AnatomicalStructure
import com.example.data.model.AnatomyTopic
import com.example.data.model.ReviewQuestion
import com.example.data.repository.SurgicalAndEquipmentData
import com.example.ui.components.StatusBadge
import com.example.ui.theme.*

@Composable
fun ObjectivesSection(objectives: List<String>) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth().testTag("objectives_card")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Flag, contentDescription = null, tint = SurgicalCyan, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Learning Objectives", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = Color.White)
            }
            Spacer(modifier = Modifier.height(10.dp))
            objectives.forEachIndexed { i, obj ->
                Row(modifier = Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.Top) {
                    Box(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(SurgicalCyan)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = obj, style = MaterialTheme.typography.bodySmall, color = TextPrimary, lineHeight = 18.sp)
                }
            }
        }
    }
}

@Composable
fun StructuresSection(
    structures: List<AnatomicalStructure>,
    selectedStructure: AnatomicalStructure?,
    onSelectStructure: (AnatomicalStructure) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("Anatomical Structures & Landmarks", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = Color.White)

        structures.forEachIndexed { index, struct ->
            val isSelected = selectedStructure?.id == struct.id
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = if (isSelected) NavySurfaceVariant else NavySurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) SurgicalCyan else BorderSubtle),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectStructure(struct) }
                    .testTag("structure_card_${struct.id}")
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(if (isSelected) SurgicalCyan else DeepObsidian)
                                    .border(1.dp, if (isSelected) Color.White else BorderSubtle, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) DeepObsidian else TextSecondary
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = struct.name,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) SurgicalCyan else Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = struct.description, style = MaterialTheme.typography.bodyMedium, color = TextPrimary, lineHeight = 20.sp)

                    if (struct.innervationOrVascular.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(verticalAlignment = Alignment.Top) {
                            Text("Innervation/Vascular: ", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = TextSecondary)
                            Text(struct.innervationOrVascular, style = MaterialTheme.typography.bodySmall, color = TextPrimary)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(DeepObsidian)
                            .padding(10.dp)
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Lightbulb, contentDescription = null, tint = WarningYellow, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Clinical Pearl", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = WarningYellow)
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = struct.clinicalPearl, style = MaterialTheme.typography.bodySmall, color = TextPrimary, lineHeight = 18.sp)

                            if (struct.relatedIntervention.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Text("Procedural Anchor: ${struct.relatedIntervention}", style = MaterialTheme.typography.labelSmall, color = SurgicalCyan)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PhysiologyAndAnesthesiaSection(topic: AnatomyTopic) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        // Normal Physiology
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = NavySurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
            modifier = Modifier.fillMaxWidth().testTag("normal_physio_card")
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = null, tint = SurgicalCyan, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Normal Physiology & Mechanisms", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = Color.White)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = topic.normalPhysiology, style = MaterialTheme.typography.bodyMedium, color = TextPrimary, lineHeight = 20.sp)
            }
        }

        // Clinical Significance
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = NavySurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.MedicalServices, contentDescription = null, tint = MintSuccess, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Clinical Significance", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = Color.White)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = topic.clinicalSignificance, style = MaterialTheme.typography.bodyMedium, color = TextPrimary, lineHeight = 20.sp)
            }
        }

        // Anesthesia Implications
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = NavySurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, SurgicalCyan.copy(alpha = 0.5f)),
            modifier = Modifier.fillMaxWidth().testTag("anesthesia_implications_card")
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Warning, contentDescription = null, tint = CrimsonDanger, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Anesthesia Implications & Management", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = SurgicalCyan)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = topic.anesthesiaImplications, style = MaterialTheme.typography.bodyMedium, color = TextPrimary, lineHeight = 20.sp)
            }
        }
    }
}

@Composable
fun ExamDistinctionsSection(distinctions: List<String>) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, WarningYellow.copy(alpha = 0.4f)),
        modifier = Modifier.fillMaxWidth().testTag("exam_distinctions_card")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.School, contentDescription = null, tint = WarningYellow, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("High-Yield Board Exam Distinctions", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = WarningYellow)
            }
            Spacer(modifier = Modifier.height(10.dp))

            distinctions.forEachIndexed { i, distinction ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = DeepObsidian),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.Top) {
                        Text(text = "${i + 1}.", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = WarningYellow)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = distinction, style = MaterialTheme.typography.bodySmall, color = TextPrimary, lineHeight = 18.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun ReviewQuizSection(questions: List<ReviewQuestion>) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Self-Assessment Review Questions", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = Color.White)

        questions.forEachIndexed { qIndex, q ->
            var selectedOption by remember(q.id) { mutableStateOf<Int?>(null) }
            val answered = selectedOption != null

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = NavySurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier.fillMaxWidth().testTag("question_card_${q.id}")
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        StatusBadge(status = "QUESTION ${qIndex + 1}", color = SurgicalCyan)
                        StatusBadge(status = q.examHighYieldTag, color = MintSuccess)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = q.question, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = Color.White)

                    Spacer(modifier = Modifier.height(10.dp))
                    q.options.forEachIndexed { optIndex, optText ->
                        val isSelected = selectedOption == optIndex
                        val isCorrect = optIndex == q.correctIndex

                        val containerColor = when {
                            !answered -> DeepObsidian
                            isSelected && isCorrect -> MintSuccess.copy(alpha = 0.2f)
                            isSelected && !isCorrect -> CrimsonDanger.copy(alpha = 0.2f)
                            isCorrect -> MintSuccess.copy(alpha = 0.15f)
                            else -> DeepObsidian
                        }

                        val borderColor = when {
                            !answered -> BorderSubtle
                            isCorrect -> MintSuccess
                            isSelected && !isCorrect -> CrimsonDanger
                            else -> BorderSubtle
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(containerColor)
                                .border(1.dp, borderColor, RoundedCornerShape(8.dp))
                                .clickable(enabled = !answered) { selectedOption = optIndex }
                                .padding(10.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "${('A' + optIndex)}.",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (answered && isCorrect) MintSuccess else TextSecondary
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = optText,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if (answered && isCorrect) MintSuccess else Color.White
                                )
                            }
                        }
                    }

                    if (answered) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(NavySurfaceVariant)
                                .padding(10.dp)
                        ) {
                            Column {
                                Text(
                                    text = if (selectedOption == q.correctIndex) "✓ Correct" else "✗ Incorrect",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (selectedOption == q.correctIndex) MintSuccess else CrimsonDanger
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = q.explanation, style = MaterialTheme.typography.bodySmall, color = TextPrimary, lineHeight = 18.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SurgicalLinkageSection(
    guideIds: List<String>,
    onNavigateToGuide: (String) -> Unit
) {
    if (guideIds.isEmpty()) return

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, SurgicalCyan.copy(alpha = 0.4f)),
        modifier = Modifier.fillMaxWidth().testTag("surgical_linkage_card")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Link, contentDescription = null, tint = SurgicalCyan, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Related Surgical Anesthesia Protocols", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = Color.White)
            }
            Spacer(modifier = Modifier.height(10.dp))

            guideIds.forEach { guideId ->
                val guide = SurgicalAndEquipmentData.surgicalGuides.find { it.id == guideId }
                if (guide != null) {
                    Button(
                        onClick = { onNavigateToGuide(guideId) },
                        colors = ButtonDefaults.buttonColors(containerColor = DeepObsidian),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(guide.procedureName, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = SurgicalCyan)
                                Text(guide.surgicalSpecialty, style = MaterialTheme.typography.labelSmall, color = TextSecondary)
                            }
                            Icon(Icons.Default.ChevronRight, contentDescription = "Open Guide", tint = SurgicalCyan)
                        }
                    }
                }
            }
        }
    }
}
