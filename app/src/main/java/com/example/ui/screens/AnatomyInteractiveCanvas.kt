package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
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
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AnatomicalStructure
import com.example.data.model.AnatomyTopic
import com.example.data.model.AssetVerificationStatus
import com.example.ui.components.StatusBadge
import com.example.ui.theme.*

@Composable
fun AnatomyInteractiveCanvas(
    topic: AnatomyTopic,
    selectedStructure: AnatomicalStructure?,
    onSelectStructure: (AnatomicalStructure) -> Unit,
    modifier: Modifier = Modifier
) {
    var scale by remember(topic.id) { mutableFloatStateOf(1.0f) }
    var offsetX by remember(topic.id) { mutableFloatStateOf(0f) }
    var offsetY by remember(topic.id) { mutableFloatStateOf(0f) }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = modifier.fillMaxWidth().testTag("anatomy_canvas_card")
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(DeepObsidian)
            ) {
                if (topic.assetMetadata.localDrawableRes != null) {
                    // Container for gesture transforms
                    BoxWithConstraints(
                        modifier = Modifier
                            .fillMaxSize()
                            .clipToBounds()
                            .pointerInput(topic.id) {
                                detectTransformGestures { _, pan, zoom, _ ->
                                    scale = (scale * zoom).coerceIn(1.0f, 4.0f)
                                    // Limit pan bounds based on scale
                                    val maxPan = 400f * (scale - 1f)
                                    offsetX = (offsetX + pan.x).coerceIn(-maxPan, maxPan)
                                    offsetY = (offsetY + pan.y).coerceIn(-maxPan, maxPan)
                                }
                            }
                    ) {
                        val containerWidth = maxWidth
                        val containerHeight = maxHeight
                        val imageAspect = topic.assetMetadata.intrinsicAspectRatio

                        // Exact ContentScale.Fit Letterbox Math:
                        val containerAspect = if (containerHeight.value > 0f) {
                            containerWidth.value / containerHeight.value
                        } else 1.777f

                        val displayedWidth: androidx.compose.ui.unit.Dp
                        val displayedHeight: androidx.compose.ui.unit.Dp
                        val renderedLeftOffset: androidx.compose.ui.unit.Dp
                        val renderedTopOffset: androidx.compose.ui.unit.Dp

                        if (containerAspect > imageAspect) {
                            // Pillarboxed: Height-constrained, black bars on left/right
                            displayedHeight = containerHeight
                            displayedWidth = containerHeight * imageAspect
                            renderedLeftOffset = (containerWidth - displayedWidth) / 2
                            renderedTopOffset = 0.dp
                        } else {
                            // Letterboxed: Width-constrained, black bars on top/bottom
                            displayedWidth = containerWidth
                            displayedHeight = containerWidth / imageAspect
                            renderedLeftOffset = 0.dp
                            renderedTopOffset = (containerHeight - displayedHeight) / 2
                        }

                        // Scaled & panned content group
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer(
                                    scaleX = scale,
                                    scaleY = scale,
                                    translationX = offsetX,
                                    translationY = offsetY
                                )
                        ) {
                            // Rendered image exactly in fitted rect
                            Image(
                                painter = painterResource(id = topic.assetMetadata.localDrawableRes),
                                contentDescription = topic.assetMetadata.assetTitle,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .testTag("anatomy_image_view")
                            )

                            // Render pinpoint markers calibrated precisely on the rendered image rect
                            topic.structures.forEachIndexed { index, struct ->
                                val isSelected = selectedStructure?.id == struct.id
                                val pinCenterX = renderedLeftOffset + (displayedWidth * struct.normalizedCoordinateX)
                                val pinCenterY = renderedTopOffset + (displayedHeight * struct.normalizedCoordinateY)

                                val pinRadius = 15.dp
                                val pinDiameter = 30.dp

                                Box(
                                    modifier = Modifier
                                        .offset(x = pinCenterX - pinRadius, y = pinCenterY - pinRadius)
                                        .size(pinDiameter)
                                        .clip(CircleShape)
                                        .background(if (isSelected) CrimsonDanger else SurgicalCyan)
                                        .border(
                                            width = if (isSelected) 2.5.dp else 1.5.dp,
                                            color = if (isSelected) Color.White else DeepObsidian,
                                            shape = CircleShape
                                        )
                                        .clickable { onSelectStructure(struct) }
                                        .testTag("pin_${struct.id}"),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${index + 1}",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp,
                                        color = if (isSelected) Color.White else DeepObsidian
                                    )
                                }
                            }
                        }
                    }

                    // Floating Controls: Zoom In / Zoom Out / Reset
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(10.dp)
                            .background(NavySurfaceVariant.copy(alpha = 0.90f), RoundedCornerShape(20.dp))
                            .border(1.dp, BorderSubtle, RoundedCornerShape(20.dp))
                            .padding(horizontal = 4.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { scale = (scale - 0.5f).coerceAtLeast(1.0f) },
                            modifier = Modifier.size(32.dp).testTag("btn_zoom_out")
                        ) {
                            Icon(Icons.Default.Remove, contentDescription = "Zoom Out", tint = Color.White, modifier = Modifier.size(16.dp))
                        }

                        IconButton(
                            onClick = {
                                scale = 1.0f
                                offsetX = 0f
                                offsetY = 0f
                            },
                            modifier = Modifier.size(32.dp).testTag("reset_zoom_button")
                        ) {
                            Icon(Icons.Default.Refresh, contentDescription = "Reset Zoom", tint = SurgicalCyan, modifier = Modifier.size(16.dp))
                        }

                        IconButton(
                            onClick = { scale = (scale + 0.5f).coerceAtMost(4.0f) },
                            modifier = Modifier.size(32.dp).testTag("btn_zoom_in")
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Zoom In", tint = Color.White, modifier = Modifier.size(16.dp))
                        }
                    }
                } else {
                    // Schematic Interactive Landmark Explorer for topics awaiting clinical asset
                    BoxWithConstraints(
                        modifier = Modifier
                            .fillMaxSize()
                            .clipToBounds()
                    ) {
                        val containerWidth = maxWidth
                        val containerHeight = maxHeight

                        // Technical Grid Background
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val w = size.width
                            val h = size.height

                            // Draw subtle grid lines
                            val gridColor = Color(0xFF1E293B)
                            for (xStep in 1..7) {
                                val x = w * (xStep / 8f)
                                drawLine(gridColor, Offset(x, 0f), Offset(x, h), strokeWidth = 1f)
                            }
                            for (yStep in 1..5) {
                                val y = h * (yStep / 6f)
                                drawLine(gridColor, Offset(0f, y), Offset(w, y), strokeWidth = 1f)
                            }

                            // Center crosshair
                            drawLine(Color(0xFF334155), Offset(w / 2f, 0f), Offset(w / 2f, h), strokeWidth = 1.5f)
                            drawLine(Color(0xFF334155), Offset(0f, h / 2f), Offset(w, h / 2f), strokeWidth = 1.5f)
                        }

                        // Schematic landmarks rendered at calibrated coordinates
                        topic.structures.forEachIndexed { index, struct ->
                            val isSelected = selectedStructure?.id == struct.id
                            val pinX = containerWidth * struct.normalizedCoordinateX
                            val pinY = containerHeight * struct.normalizedCoordinateY

                            Box(
                                modifier = Modifier
                                    .offset(x = pinX - 16.dp, y = pinY - 16.dp)
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(if (isSelected) CrimsonDanger else WarningYellow)
                                    .border(
                                        width = if (isSelected) 2.5.dp else 1.5.dp,
                                        color = if (isSelected) Color.White else DeepObsidian,
                                        shape = CircleShape
                                    )
                                    .clickable { onSelectStructure(struct) }
                                    .testTag("pin_${struct.id}"),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    color = DeepObsidian
                                )
                            }
                        }

                        // Center Status Note
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .background(DeepObsidian.copy(alpha = 0.88f), RoundedCornerShape(10.dp))
                                .border(1.dp, WarningYellow.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
                                .padding(horizontal = 14.dp, vertical = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Info, contentDescription = null, tint = WarningYellow, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Verified Medical Diagram Pending",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = WarningYellow
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Calibrated schematic grid active • Tap landmark pins to inspect",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextSecondary,
                                fontSize = 10.sp
                            )
                        }
                    }
                }

                // Top Left Overlay Badge
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp)
                        .background(DeepObsidian.copy(alpha = 0.88f), RoundedCornerShape(8.dp))
                        .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = if (topic.assetMetadata.localDrawableRes != null) Icons.Default.TouchApp else Icons.Default.GridOn,
                            contentDescription = null,
                            tint = if (topic.assetMetadata.localDrawableRes != null) SurgicalCyan else WarningYellow,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (topic.assetMetadata.localDrawableRes != null)
                                "Interactive 2D Landmarks (${topic.structures.size} Pins)"
                            else
                                "Calibrated Schematic Grid (${topic.structures.size} Pins)",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            fontSize = 11.sp
                        )
                    }
                }
            }

            // Diagram Header details
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StatusBadge(status = topic.category.displayName.uppercase(), color = SurgicalCyan)
                    val statusColor = when (topic.assetMetadata.status) {
                        AssetVerificationStatus.CLINICALLY_REVIEWED -> MintSuccess
                        AssetVerificationStatus.SOURCE_VERIFIED, AssetVerificationStatus.LICENSE_VERIFIED -> SurgicalCyan
                        AssetVerificationStatus.IMPORTED_UNVERIFIED -> WarningYellow
                        AssetVerificationStatus.MISSING, AssetVerificationStatus.NEEDS_CORRECTION -> CrimsonDanger
                    }
                    StatusBadge(status = topic.assetMetadata.status.displayName.uppercase(), color = statusColor)
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = topic.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = topic.subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = TextSecondary
                )
            }
        }
    }
}
