package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
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
import com.example.data.model.LearningPathway
import com.example.data.model.StudyLevel
import com.example.ui.theme.*
import com.example.ui.viewmodel.AnesthesiaViewModel

@Composable
fun PathwayDialog(
    viewModel: AnesthesiaViewModel,
    onDismiss: () -> Unit
) {
    val currentPathway by viewModel.pathway.collectAsState()
    val currentLevel by viewModel.studyLevel.collectAsState()

    var selectedPathway by remember { mutableStateOf(currentPathway) }
    var selectedLevel by remember { mutableStateOf(currentLevel) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = NavySurface,
            border = androidx.compose.foundation.BorderStroke(1.dp, SurgicalCyan.copy(alpha = 0.5f)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag("pathway_dialog")
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Learning Pathway & Level",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp).minimumInteractiveComponentSize()) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "Close", tint = TextSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Select Primary Educational Track:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = SurgicalCyan
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Pathway Options
                LearningPathway.entries.forEach { pw ->
                    val isSelected = selectedPathway == pw
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = if (isSelected) NavySurfaceVariant else NavySurface,
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (isSelected) SurgicalCyan else BorderSubtle
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { selectedPathway = pw }
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = pw.displayName,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) SurgicalCyan else Color.White
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = pw.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextSecondary,
                                    fontSize = 11.sp,
                                    lineHeight = 15.sp
                                )
                            }
                            if (isSelected) {
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = "Selected",
                                    tint = SurgicalCyan
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Study / Training Stage:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = SurgicalCyan
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Study Level Selection
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    StudyLevel.entries.forEach { lvl ->
                        val isSelected = selectedLevel == lvl
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (isSelected) NavySurfaceVariant else Color.Transparent,
                            border = androidx.compose.foundation.BorderStroke(
                                0.5.dp,
                                if (isSelected) ClinicalTeal else BorderSubtle
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { selectedLevel = lvl }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = lvl.displayName,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if (isSelected) ClinicalTeal else TextPrimary,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                                if (isSelected) {
                                    Icon(
                                        imageVector = Icons.Filled.CheckCircle,
                                        contentDescription = null,
                                        tint = ClinicalTeal,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        viewModel.updatePathway(selectedPathway)
                        viewModel.updateStudyLevel(selectedLevel)
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SurgicalCyan,
                        contentColor = DeepObsidian
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth().minimumInteractiveComponentSize().testTag("save_pathway_button")
                ) {
                    Text("Apply & Save Preferences", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
