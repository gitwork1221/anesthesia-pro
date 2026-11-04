package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun PharmacologyVisualSection(
    diagramType: String?,
    diagramTitle: String?,
    pkCurveType: String?,
    drugName: String,
    modifier: Modifier = Modifier
) {
    if (diagramType == null && pkCurveType == null) return

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, SurgicalCyan.copy(alpha = 0.5f)),
        modifier = modifier.fillMaxWidth().testTag("visual_pharmacology_card")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Biotech,
                        contentDescription = null,
                        tint = SurgicalCyan,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Visual Mechanism & PK Modeling",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                StatusBadge(status = "Interactive Schematic", color = ClinicalTeal)
            }

            if (diagramType != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = diagramTitle ?: "Receptor Target Architecture",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = SurgicalCyan
                )
                Spacer(modifier = Modifier.height(8.dp))
                ReceptorMechanismCanvas(diagramType = diagramType, drugName = drugName)
            }

            if (pkCurveType != null) {
                Spacer(modifier = Modifier.height(14.dp))
                HorizontalDivider(color = BorderSubtle)
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Pharmacokinetic Time-Course & Distribution Model",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = ElectricBlue
                )
                Spacer(modifier = Modifier.height(8.dp))
                PkCurveCanvas(pkCurveType = pkCurveType, drugName = drugName)
            }
        }
    }
}

@Composable
fun ReceptorMechanismCanvas(
    diagramType: String,
    drugName: String,
    modifier: Modifier = Modifier
) {
    var selectedHotspot by remember { mutableStateOf<String?>(null) }

    Column(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(NavySurfaceVariant)
                .border(1.dp, BorderSubtle, RoundedCornerShape(10.dp))
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val w = size.width
                val h = size.height

                // Draw lipid bilayer membrane across middle
                val memY1 = h * 0.42f
                val memY2 = h * 0.58f
                drawRect(
                    color = Color(0xFF1E2D4A),
                    topLeft = Offset(0f, memY1),
                    size = Size(w, memY2 - memY1)
                )

                // Membrane polar head groups
                val circleRadius = 4f
                val step = 14f
                var x = 8f
                while (x < w) {
                    drawCircle(color = Color(0xFF4A6572), radius = circleRadius, center = Offset(x, memY1))
                    drawCircle(color = Color(0xFF4A6572), radius = circleRadius, center = Offset(x, memY2))
                    x += step
                }

                when (diagramType) {
                    "GABA_A_RECEPTOR" -> {
                        // Draw pentameric channel pore in center
                        val poreCenterX = w * 0.5f
                        val channelWidth = w * 0.28f
                        drawRoundRect(
                            color = ClinicalTeal.copy(alpha = 0.85f),
                            topLeft = Offset(poreCenterX - channelWidth / 2f, h * 0.22f),
                            size = Size(channelWidth, h * 0.56f),
                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(12f, 12f)
                        )
                        // Central channel pore
                        drawRect(
                            color = Color(0xFF0A1128),
                            topLeft = Offset(poreCenterX - 14f, h * 0.22f),
                            size = Size(28f, h * 0.56f)
                        )
                        // Chloride ion path (Cl- arrow pointing downward into ICF)
                        val clPath = Path().apply {
                            moveTo(poreCenterX, h * 0.12f)
                            lineTo(poreCenterX, h * 0.88f)
                        }
                        drawPath(clPath, color = SurgicalCyan, style = Stroke(width = 4f, cap = StrokeCap.Round))
                        // Arrowhead
                        drawPath(
                            Path().apply {
                                moveTo(poreCenterX - 8f, h * 0.82f)
                                lineTo(poreCenterX, h * 0.92f)
                                lineTo(poreCenterX + 8f, h * 0.82f)
                            },
                            color = SurgicalCyan,
                            style = Stroke(width = 4f, cap = StrokeCap.Round)
                        )
                    }
                    "MU_OPIOID_GPCR" -> {
                        // 7 Transmembrane helical snake
                        val startX = w * 0.25f
                        val helixW = (w * 0.5f) / 7f
                        for (i in 0 until 7) {
                            val hx = startX + i * helixW + helixW / 2f
                            drawLine(
                                color = ElectricBlue,
                                start = Offset(hx, h * 0.25f),
                                end = Offset(hx, h * 0.75f),
                                strokeWidth = 8f,
                                cap = StrokeCap.Round
                            )
                        }
                        // Intracellular G-protein alpha/beta/gamma trimer
                        drawCircle(color = AlertAmber, radius = 16f, center = Offset(w * 0.5f, h * 0.82f))
                        drawCircle(color = AlertAmber.copy(alpha = 0.7f), radius = 12f, center = Offset(w * 0.5f - 24f, h * 0.85f))
                        drawCircle(color = AlertAmber.copy(alpha = 0.7f), radius = 12f, center = Offset(w * 0.5f + 24f, h * 0.85f))
                    }
                    "NMJ_NICOTINIC_ACHR" -> {
                        // NMJ postsynaptic receptor & junctional cleft
                        val nmjCenterX = w * 0.5f
                        drawRoundRect(
                            color = AlertAmber.copy(alpha = 0.85f),
                            topLeft = Offset(nmjCenterX - w * 0.15f, h * 0.25f),
                            size = Size(w * 0.30f, h * 0.50f),
                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(10f, 10f)
                        )
                        // Na+ influx and K+ efflux indicators
                        drawLine(
                            color = VitalGreen,
                            start = Offset(nmjCenterX - 10f, h * 0.15f),
                            end = Offset(nmjCenterX - 10f, h * 0.85f),
                            strokeWidth = 4f,
                            cap = StrokeCap.Round
                        )
                        drawLine(
                            color = AlarmRose,
                            start = Offset(nmjCenterX + 10f, h * 0.85f),
                            end = Offset(nmjCenterX + 10f, h * 0.15f),
                            strokeWidth = 3f,
                            cap = StrokeCap.Round
                        )
                    }
                    "VOLTAGE_GATED_SODIUM_CHANNEL" -> {
                        // S4 Voltage Sensor & Inactivation Gate
                        val naCenterX = w * 0.5f
                        drawRoundRect(
                            color = SurgicalCyan.copy(alpha = 0.85f),
                            topLeft = Offset(naCenterX - w * 0.16f, h * 0.25f),
                            size = Size(w * 0.32f, h * 0.50f),
                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(10f, 10f)
                        )
                        // Inactivation gate plug (ball and chain / hinged lid)
                        drawCircle(color = AlarmRose, radius = 14f, center = Offset(naCenterX, h * 0.75f))
                    }
                    else -> {
                        // General Receptor / Signal cascade
                        drawCircle(color = SurgicalCyan, radius = 24f, center = Offset(w * 0.5f, h * 0.5f))
                    }
                }
            }

            // Annotation labels inside canvas
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Extracellular Fluid (ECF)", style = MaterialTheme.typography.labelSmall, color = TextMuted)
                    when (diagramType) {
                        "GABA_A_RECEPTOR" -> Text("High [Cl⁻] ~105 mEq/L", style = MaterialTheme.typography.labelSmall, color = SurgicalCyan)
                        "NMJ_NICOTINIC_ACHR" -> Text("ACh Cleft / High [Na⁺]", style = MaterialTheme.typography.labelSmall, color = VitalGreen)
                        "VOLTAGE_GATED_SODIUM_CHANNEL" -> Text("Uncharged (B) Crosses Membrane", style = MaterialTheme.typography.labelSmall, color = ClinicalTeal)
                        else -> Text("Receptor Binding Pocket", style = MaterialTheme.typography.labelSmall, color = TextSecondary)
                    }
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Intracellular Fluid (ICF)", style = MaterialTheme.typography.labelSmall, color = TextMuted)
                    when (diagramType) {
                        "GABA_A_RECEPTOR" -> Text("Hyperpolarization (-70 to -85 mV)", style = MaterialTheme.typography.labelSmall, color = ClinicalTeal)
                        "MU_OPIOID_GPCR" -> Text("Gi/o ↓cAMP, ↑K⁺ Outflow", style = MaterialTheme.typography.labelSmall, color = AlertAmber)
                        "NMJ_NICOTINIC_ACHR" -> Text("Endplate Potential (EPP)", style = MaterialTheme.typography.labelSmall, color = AlertAmber)
                        "VOLTAGE_GATED_SODIUM_CHANNEL" -> Text("Charged (BH⁺) Plugs Internal Pore", style = MaterialTheme.typography.labelSmall, color = AlarmRose)
                        else -> Text("Downstream Effector Cascade", style = MaterialTheme.typography.labelSmall, color = TextSecondary)
                    }
                }
            }
        }

        // Explanatory breakdown chips
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            when (diagramType) {
                "GABA_A_RECEPTOR" -> {
                    ExplanationChip(title = "Pore Subunits", subtitle = "2α, 2β, 1γ pentamer", modifier = Modifier.weight(1f))
                    ExplanationChip(title = "Chloride Current", subtitle = "Postsynaptic inhibition", modifier = Modifier.weight(1f))
                }
                "MU_OPIOID_GPCR" -> {
                    ExplanationChip(title = "G-Protein Transduction", subtitle = "Gi inhibits Adenylyl Cyclase", modifier = Modifier.weight(1f))
                    ExplanationChip(title = "Hyperpolarization", subtitle = "GIRK K⁺ channel opening", modifier = Modifier.weight(1f))
                }
                "NMJ_NICOTINIC_ACHR" -> {
                    ExplanationChip(title = "Receptor Structure", subtitle = "α2β1δε subunits", modifier = Modifier.weight(1f))
                    ExplanationChip(title = "Block Type", subtitle = "Competitive vs Depolarizing", modifier = Modifier.weight(1f))
                }
                "VOLTAGE_GATED_SODIUM_CHANNEL" -> {
                    ExplanationChip(title = "Hydrophobic Route", subtitle = "Lipid diffuse in uncharged form", modifier = Modifier.weight(1f))
                    ExplanationChip(title = "Hydrophilic Block", subtitle = "Ionized state blocks open channel", modifier = Modifier.weight(1f))
                }
                else -> {
                    ExplanationChip(title = "Drug-Target Binding", subtitle = "Specific receptor affinity", modifier = Modifier.weight(1f))
                    ExplanationChip(title = "Pharmacodynamics", subtitle = "Signal amplification", modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun PkCurveCanvas(
    pkCurveType: String,
    drugName: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(NavySurfaceVariant)
                .border(1.dp, BorderSubtle, RoundedCornerShape(10.dp))
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val w = size.width
                val h = size.height

                // Draw Axes
                val padX = 36f
                val padY = 24f
                val originX = padX
                val originY = h - padY

                // Grid lines
                drawLine(color = BorderSubtle, start = Offset(originX, originY), end = Offset(w - 16f, originY), strokeWidth = 2f)
                drawLine(color = BorderSubtle, start = Offset(originX, originY), end = Offset(originX, 16f), strokeWidth = 2f)

                // Minimum Effective Concentration (MEC) dashed line
                val mecY = originY - (originY - 16f) * 0.35f
                var dashX = originX
                while (dashX < w - 16f) {
                    drawLine(color = AlertAmber.copy(alpha = 0.6f), start = Offset(dashX, mecY), end = Offset(dashX + 8f, mecY), strokeWidth = 1.5f)
                    dashX += 14f
                }

                // Minimum Toxic Concentration (MTC) dashed line
                val mtcY = originY - (originY - 16f) * 0.85f
                dashX = originX
                while (dashX < w - 16f) {
                    drawLine(color = AlarmRose.copy(alpha = 0.6f), start = Offset(dashX, mtcY), end = Offset(dashX + 8f, mtcY), strokeWidth = 1.5f)
                    dashX += 14f
                }

                when (pkCurveType) {
                    "MULTI_COMPARTMENT_DISTRIBUTION" -> {
                        // Rapid IV bolus curve: Instant peak, rapid alpha distribution drop, slow beta elimination slope
                        val path = Path().apply {
                            moveTo(originX, originY)
                            // Sudden steep spike to peak
                            quadraticTo(originX + 15f, mtcY + 10f, originX + 30f, mtcY + 15f)
                            // Alpha distribution rapid decline
                            cubicTo(originX + 80f, mecY + 15f, originX + 160f, mecY + 35f, w - 24f, originY - 10f)
                        }
                        drawPath(path, color = SurgicalCyan, style = Stroke(width = 3.5f, cap = StrokeCap.Round))

                        // Brain effect-site concentration (ke0 hysteresis lag)
                        val effectSitePath = Path().apply {
                            moveTo(originX, originY)
                            // Slower peak (hysteresis)
                            cubicTo(originX + 45f, originY - 10f, originX + 70f, mecY - 20f, originX + 90f, mecY - 25f)
                            // Redistribution tracking
                            cubicTo(originX + 140f, mecY - 10f, originX + 200f, mecY + 20f, w - 24f, originY - 8f)
                        }
                        drawPath(effectSitePath, color = ClinicalTeal, style = Stroke(width = 2.5f, cap = StrokeCap.Round))
                    }
                    "CONTEXT_SENSITIVE_HALF_TIME" -> {
                        // Flat vs climbing context-sensitive half time
                        val path = Path().apply {
                            moveTo(originX, originY - 20f)
                            // Modest rise curve
                            cubicTo(w * 0.35f, originY - 35f, w * 0.65f, originY - 65f, w - 24f, originY - 95f)
                        }
                        drawPath(path, color = ElectricBlue, style = Stroke(width = 3.5f, cap = StrokeCap.Round))
                    }
                    else -> {
                        // Standard Single Dose absorption and elimination curve
                        val path = Path().apply {
                            moveTo(originX, originY)
                            cubicTo(originX + 60f, mtcY + 20f, originX + 110f, mtcY + 20f, originX + 160f, mecY)
                            cubicTo(originX + 220f, mecY + 30f, originX + 300f, originY - 15f, w - 24f, originY - 8f)
                        }
                        drawPath(path, color = SurgicalCyan, style = Stroke(width = 3.5f, cap = StrokeCap.Round))
                    }
                }
            }

            // PK Legend
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Plasma Concentration [Cp]", style = MaterialTheme.typography.labelSmall, color = TextMuted)
                    Text("MTC (Toxicity Threshold)", style = MaterialTheme.typography.labelSmall, color = AlarmRose.copy(alpha = 0.8f))
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Time (hours / min)", style = MaterialTheme.typography.labelSmall, color = TextMuted)
                    Text("MEC (Therapeutic Floor)", style = MaterialTheme.typography.labelSmall, color = AlertAmber.copy(alpha = 0.8f))
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(8.dp).background(SurgicalCyan, RoundedCornerShape(2.dp)))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Plasma [Cp] Curve", style = MaterialTheme.typography.labelSmall, color = TextSecondary)
            }
            if (pkCurveType == "MULTI_COMPARTMENT_DISTRIBUTION") {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(8.dp).background(ClinicalTeal, RoundedCornerShape(2.dp)))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Brain Effect-Site (ke0)", style = MaterialTheme.typography.labelSmall, color = TextSecondary)
                }
            }
        }
    }
}

@Composable
private fun ExplanationChip(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(NavySurfaceVariant)
            .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
            .padding(6.dp)
    ) {
        Column {
            Text(text = title, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = subtitle, style = MaterialTheme.typography.labelSmall, color = TextSecondary, fontSize = 11.sp)
        }
    }
}
