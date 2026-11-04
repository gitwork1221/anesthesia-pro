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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DrugDosing
import com.example.ui.components.PharmacologyVisualSection
import com.example.ui.components.StatusBadge
import com.example.ui.theme.*
import com.example.ui.viewmodel.AnesthesiaViewModel
import com.example.ui.viewmodel.NavigationDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrugDetailScreen(
    drugId: String,
    viewModel: AnesthesiaViewModel,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val drug = viewModel.getDrug(drugId)
    val bookmarks by viewModel.bookmarks.collectAsState()
    val isBookmarked = bookmarks.any { it.id == drugId }

    if (drug == null) {
        Box(
            modifier = modifier.fillMaxSize().background(DeepObsidian),
            contentAlignment = Alignment.Center
        ) {
            Text("Drug monograph not found", color = TextSecondary)
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = drug.genericName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Trade: ${drug.brandNames}",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                    }
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
                        onClick = {
                            viewModel.toggleBookmark(
                                id = drug.id,
                                type = "drug",
                                title = drug.genericName,
                                subtitle = "${drug.category.displayName} • ${drug.drugClass}"
                            )
                        },
                        modifier = Modifier.minimumInteractiveComponentSize().testTag("bookmark_drug_button")
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                            contentDescription = "Bookmark Drug",
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
            // Header Overview Card
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = NavySurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                    modifier = Modifier.fillMaxWidth().testTag("drug_header_card")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            StatusBadge(status = drug.category.displayName, color = SurgicalCyan)
                            StatusBadge(status = drug.reviewStatus, color = ClinicalTeal)
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = drug.genericName,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Text(
                            text = drug.drugClass,
                            style = MaterialTheme.typography.labelMedium,
                            color = TextSecondary,
                            fontWeight = FontWeight.Normal
                        )

                        if (drug.synonyms.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Synonyms: ${drug.synonyms.joinToString(", ")}",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextMuted
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Target Receptor: ${drug.targetReceptor}",
                            style = MaterialTheme.typography.labelMedium,
                            color = ClinicalTeal,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = drug.mechanismOfAction,
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextPrimary,
                            lineHeight = 20.sp
                        )

                        if (drug.formulationsAndRoutes.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Formulations & Administration:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = AlertAmber
                            )
                            drug.formulationsAndRoutes.forEach { form ->
                                Row(modifier = Modifier.padding(vertical = 1.dp), verticalAlignment = Alignment.Top) {
                                    Text("• ", color = AlertAmber, fontWeight = FontWeight.Bold)
                                    Text(text = form, color = TextSecondary, style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                    }
                }
            }

            // Explicit Review Warning Banner if applicable
            if (drug.reviewStatus.contains("Warning", ignoreCase = true) || drug.reviewStatus.contains("Off-Label", ignoreCase = true)) {
                item {
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = AlarmRose.copy(alpha = 0.15f)),
                        border = androidx.compose.foundation.BorderStroke(1.dp, AlarmRose),
                        modifier = Modifier.fillMaxWidth().testTag("review_warning_banner")
                    ) {
                        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Filled.Warning, contentDescription = null, tint = AlarmRose)
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text("CLINICAL REVIEW WARNING", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = AlarmRose)
                                Text(drug.reviewStatus, style = MaterialTheme.typography.bodySmall, color = TextPrimary)
                            }
                        }
                    }
                }
            }

            // Structured Dosing Protocols Card
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = NavySurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SurgicalCyan.copy(alpha = 0.6f)),
                    modifier = Modifier.fillMaxWidth().testTag("dosing_protocols_card")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Filled.Scale, contentDescription = null, tint = SurgicalCyan, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Structured Dosing & Clinical Administration",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Specific Dosing Protocols
                        if (drug.dosingProtocols.isNotEmpty()) {
                            drug.dosingProtocols.forEachIndexed { index, proto ->
                                if (index > 0) HorizontalDivider(color = BorderSubtle, modifier = Modifier.padding(vertical = 8.dp))
                                Text(
                                    text = "${proto.indication} (${proto.route})",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = SurgicalCyan
                                )
                                Text(text = "Adult: ${proto.adultDose}", style = MaterialTheme.typography.bodySmall, color = TextPrimary)
                                if (proto.pediatricDose != null) {
                                    Text(text = "Pediatric: ${proto.pediatricDose}", style = MaterialTheme.typography.bodySmall, color = ClinicalTeal)
                                }
                                if (proto.elderlyDose != null) {
                                    Text(text = "Geriatric / Elderly: ${proto.elderlyDose}", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                                }
                                if (proto.maxDose != null) {
                                    Text(text = "Maximum Dose: ${proto.maxDose}", style = MaterialTheme.typography.bodySmall, color = AlertAmber, fontWeight = FontWeight.SemiBold)
                                }
                                if (proto.unitNotes != null) {
                                    Text(text = "Weight Basis / Note: ${proto.unitNotes}", style = MaterialTheme.typography.bodySmall, color = TextMuted)
                                }
                            }
                        } else {
                            // General Adult and Pediatric fallbacks
                            Text(text = "Adult Administration:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = SurgicalCyan)
                            Text(text = drug.adultDosing, style = MaterialTheme.typography.bodyMedium, color = TextPrimary, lineHeight = 20.sp)

                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Pediatric Administration:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = ClinicalTeal)
                            Text(text = drug.pediatricDosing, style = MaterialTheme.typography.bodyMedium, color = TextPrimary, lineHeight = 20.sp)
                        }

                        if (drug.obstetricDosing.isNotBlank() && drug.obstetricDosing != "See Obstetric & Perinatal section") {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Obstetric Dosing Considerations:", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = ElectricBlue)
                            Text(text = drug.obstetricDosing, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                        }
                    }
                }
            }

            // Visual Pharmacology & Mechanism Diagram
            if (drug.diagramType != null || drug.pkCurveType != null) {
                item {
                    PharmacologyVisualSection(
                        diagramType = drug.diagramType,
                        diagramTitle = drug.diagramTitle,
                        pkCurveType = drug.pkCurveType,
                        drugName = drug.genericName
                    )
                }
            }

            // Pharmacokinetics & Timing Metrics
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = NavySurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                    modifier = Modifier.fillMaxWidth().testTag("pharmacokinetics_card")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Filled.HourglassTop, contentDescription = null, tint = ElectricBlue, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Pharmacokinetics & Clinical Timing", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Grid of 4 timing boxes if populated
                        if (drug.onset.isNotBlank() || drug.duration.isNotBlank()) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                TimingBox(label = "Onset", value = drug.onset.ifBlank { "Rapid" }, modifier = Modifier.weight(1f))
                                TimingBox(label = "Peak Effect", value = drug.peakEffect.ifBlank { "Variable" }, modifier = Modifier.weight(1f))
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                TimingBox(label = "Duration", value = drug.duration.ifBlank { "Short" }, modifier = Modifier.weight(1f))
                                TimingBox(label = "Elimination", value = drug.elimination.ifBlank { "Hepatic/Renal" }, modifier = Modifier.weight(1f))
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }

                        Text(text = drug.onsetAndDuration, style = MaterialTheme.typography.bodyMedium, color = TextPrimary, lineHeight = 20.sp)

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Metabolism & Clearance Mechanics:", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = TextSecondary)
                        Text(text = drug.pharmacokinetics, style = MaterialTheme.typography.bodySmall, color = TextSecondary, lineHeight = 18.sp)
                    }
                }
            }

            // Special Populations & Organ Dysfunction
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = NavySurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Filled.People, contentDescription = null, tint = ClinicalTeal, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Organ Dysfunction & Special Populations", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        PopulationRow(label = "Renal Impairment", details = drug.renalAdjustment)
                        PopulationRow(label = "Hepatic Impairment", details = drug.hepaticAdjustment)
                        PopulationRow(label = "Pregnancy & Lactation", details = drug.pregnancyAndLactation)
                        PopulationRow(label = "Geriatric Considerations", details = drug.elderlyConsiderations)
                    }
                }
            }

            // Contraindications & Warnings
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = NavySurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, AlarmRose.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Filled.ReportProblem, contentDescription = null, tint = AlarmRose, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Contraindications & Cautions", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Absolute Contraindications:", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = AlarmRose)
                        drug.contraindications.forEach { ci ->
                            Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
                                Text("• ", color = AlarmRose, fontWeight = FontWeight.Bold)
                                Text(text = ci, color = TextPrimary, style = MaterialTheme.typography.bodySmall)
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Clinical Cautions & Monitoring:", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = AlertAmber)
                        drug.cautions.forEach { caution ->
                            Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
                                Text("• ", color = AlertAmber, fontWeight = FontWeight.Bold)
                                Text(text = caution, color = TextSecondary, style = MaterialTheme.typography.bodySmall)
                            }
                        }

                        if (drug.interactions.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Important Drug Interactions:", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = ElectricBlue)
                            drug.interactions.forEach { inter ->
                                Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
                                    Text("• ", color = ElectricBlue, fontWeight = FontWeight.Bold)
                                    Text(text = inter, color = TextSecondary, style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                    }
                }
            }

            // Toxicity Recognition & Management Protocol
            if (drug.toxicityManagement != null || drug.reversalAgent != null) {
                item {
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = NavySurfaceVariant),
                        border = androidx.compose.foundation.BorderStroke(1.dp, AlarmRose.copy(alpha = 0.7f)),
                        modifier = Modifier.fillMaxWidth().testTag("toxicity_protocol_card")
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Filled.MedicalServices, contentDescription = null, tint = AlarmRose, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Toxicity Recognition & Emergency Protocol", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
                            }

                            if (drug.reversalAgent != null) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = "Antidote / Specific Reversal:", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = VitalGreen)
                                Text(text = drug.reversalAgent, style = MaterialTheme.typography.bodyMedium, color = TextPrimary, lineHeight = 20.sp)
                            }

                            if (drug.toxicityManagement != null) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = "Emergency Management Steps:", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = AlertAmber)
                                Text(text = drug.toxicityManagement, style = MaterialTheme.typography.bodySmall, color = TextPrimary, lineHeight = 18.sp)
                            }
                        }
                    }
                }
            }

            // Clinical Pearls
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = NavySurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, AlertAmber.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Filled.Lightbulb, contentDescription = null, tint = AlertAmber, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Clinical Pearls & OR Practice", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = drug.clinicalPearls, style = MaterialTheme.typography.bodyMedium, color = TextPrimary, lineHeight = 20.sp)
                    }
                }
            }

            // Related Clinical Protocols (Cross-linking to Surgical Guides & Crisis Simulations)
            if (drug.relatedGuideIds.isNotEmpty() || drug.relatedCaseIds.isNotEmpty()) {
                item {
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = NavySurface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SurgicalCyan.copy(alpha = 0.4f)),
                        modifier = Modifier.fillMaxWidth().testTag("related_protocols_card")
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Filled.Link, contentDescription = null, tint = SurgicalCyan, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Related Surgical Protocols & Crisis Cases", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
                            }
                            Spacer(modifier = Modifier.height(8.dp))

                            drug.relatedGuideIds.forEach { guideId ->
                                val guide = viewModel.getSurgicalGuide(guideId)
                                if (guide != null) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(NavySurfaceVariant)
                                            .clickable { viewModel.navigateTo(NavigationDestination.SurgicalGuideDetail(guideId)) }
                                            .padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(text = guide.procedureName, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = Color.White)
                                            Text(text = guide.surgicalSpecialty, style = MaterialTheme.typography.labelSmall, color = TextSecondary)
                                        }
                                        Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = null, tint = SurgicalCyan)
                                    }
                                }
                            }

                            drug.relatedCaseIds.forEach { caseId ->
                                val simCase = viewModel.getClinicalCase(caseId)
                                if (simCase != null) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(NavySurfaceVariant)
                                            .clickable { viewModel.navigateTo(NavigationDestination.CaseSimDetail(caseId)) }
                                            .padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(text = simCase.title, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = Color.White)
                                            Text(text = "Crisis Sim • ${simCase.category}", style = MaterialTheme.typography.labelSmall, color = AlertAmber)
                                        }
                                        Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = null, tint = AlertAmber)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Traceable References & Content Governance
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = NavySurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                    modifier = Modifier.fillMaxWidth().testTag("traceable_references_card")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Filled.MenuBook, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Traceable Clinical Evidence & Governance", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        if (drug.references.isNotEmpty()) {
                            drug.references.forEach { ref ->
                                Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
                                    Text("• ", color = SurgicalCyan, fontWeight = FontWeight.Bold)
                                    Column {
                                        Text(text = ref.sourceTitle, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = Color.White)
                                        Text(text = "${ref.organizationOrJournal} • ${ref.editionOrDate} • ${ref.reviewStatus}", style = MaterialTheme.typography.labelSmall, color = TextMuted)
                                    }
                                }
                            }
                        } else {
                            Text(
                                text = "Miller's Anesthesia (9th Ed), Morgan & Mikhail's Clinical Anesthesiology (7th Ed), ASA/ASRA Practice Guidelines",
                                style = MaterialTheme.typography.bodySmall,
                                color = TextSecondary
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Status: ${drug.reviewStatus} • Fully Offline Reference",
                            style = MaterialTheme.typography.labelSmall,
                            color = ClinicalTeal
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun TimingBox(label: String, value: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(NavySurfaceVariant)
            .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Column {
            Text(text = label, style = MaterialTheme.typography.labelSmall, color = TextSecondary, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = value, style = MaterialTheme.typography.bodySmall, color = Color.White, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun PopulationRow(label: String, details: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = ClinicalTeal)
        Text(text = details, style = MaterialTheme.typography.bodySmall, color = TextPrimary)
    }
}
