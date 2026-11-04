package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.data.model.Drug
import com.example.data.model.DrugCategory
import com.example.data.model.EquipmentModule
import com.example.data.model.SurgicalGuide
import com.example.data.model.SurgicalSpecialty
import com.example.ui.components.StatusBadge
import com.example.ui.theme.*
import com.example.ui.viewmodel.AnesthesiaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PharmacologyAndReferenceScreen(
    viewModel: AnesthesiaViewModel,
    onNavigateToDrug: (String) -> Unit,
    onNavigateToGuide: (String) -> Unit,
    onNavigateToEquipment: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTopTab by remember { mutableIntStateOf(0) } // 0: Drugs, 1: Surgical Guides, 2: Equipment
    var drugSearchQuery by remember { mutableStateOf("") }
    var selectedCategoryFilter by remember { mutableStateOf<DrugCategory?>(null) }
    val selectedClassFilter by viewModel.selectedDrugClassFilter.collectAsState()

    var guideSearchQuery by remember { mutableStateOf("") }
    var selectedSpecialtyFilter by remember { mutableStateOf<SurgicalSpecialty?>(null) }

    val allDrugs = viewModel.getDrugs()
    val allGuides = viewModel.getSurgicalGuides()
    val allEquipment = viewModel.getEquipmentModules()

    val filteredDrugs = remember(drugSearchQuery, selectedCategoryFilter, selectedClassFilter, allDrugs) {
        val q = drugSearchQuery.trim().lowercase()
        allDrugs.filter { drug ->
            val matchesCategory = selectedCategoryFilter == null || drug.category == selectedCategoryFilter
            val matchesClass = selectedClassFilter == null || drug.drugClass.contains(selectedClassFilter!!, ignoreCase = true)
            val matchesQuery = q.isBlank() ||
                    drug.genericName.lowercase().contains(q) ||
                    drug.brandNames.lowercase().contains(q) ||
                    drug.synonyms.any { it.lowercase().contains(q) } ||
                    drug.drugClass.lowercase().contains(q) ||
                    drug.targetReceptor.lowercase().contains(q) ||
                    drug.indications.any { it.lowercase().contains(q) }

            matchesCategory && matchesClass && matchesQuery
        }
    }

    val filteredGuides = remember(guideSearchQuery, selectedSpecialtyFilter, allGuides) {
        val q = guideSearchQuery.trim().lowercase()
        allGuides.filter { guide ->
            val matchesSpecialty = selectedSpecialtyFilter == null ||
                    guide.surgicalSpecialty.contains(selectedSpecialtyFilter!!.displayName, ignoreCase = true) ||
                    (selectedSpecialtyFilter == SurgicalSpecialty.EMERGENCY_SURGERY && guide.surgicalSpecialty.contains("Emergency", ignoreCase = true))
            val matchesQuery = q.isBlank() ||
                    guide.procedureName.lowercase().contains(q) ||
                    guide.surgicalSpecialty.lowercase().contains(q) ||
                    guide.overview.lowercase().contains(q) ||
                    guide.anestheticTechnique.lowercase().contains(q)
            matchesSpecialty && matchesQuery
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DeepObsidian)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // Screen title
        Text(
            text = "Clinical Reference Library",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "Verified offline pharmacology, surgical protocols & machine physics",
            style = MaterialTheme.typography.labelSmall,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Top Primary Tab Selector
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
                        Icon(imageVector = Icons.Filled.Medication, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Drugs (${allDrugs.size})", style = MaterialTheme.typography.labelSmall)
                    }
                }
            )
            Tab(
                selected = selectedTopTab == 1,
                onClick = { selectedTopTab = 1 },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Filled.LocalHospital, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Surgical (${allGuides.size})", style = MaterialTheme.typography.labelSmall)
                    }
                }
            )
            Tab(
                selected = selectedTopTab == 2,
                onClick = { selectedTopTab = 2 },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Filled.PrecisionManufacturing, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Equipment (${allEquipment.size})", style = MaterialTheme.typography.labelSmall)
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        when (selectedTopTab) {
            0 -> {
                // DRUGS SECTION
                OutlinedTextField(
                    value = drugSearchQuery,
                    onValueChange = { drugSearchQuery = it },
                    placeholder = { Text("Search by generic, brand, or receptor...", color = TextMuted) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = null, tint = SurgicalCyan)
                    },
                    trailingIcon = {
                        if (drugSearchQuery.isNotBlank()) {
                            IconButton(onClick = { drugSearchQuery = "" }) {
                                Icon(imageVector = Icons.Filled.Close, contentDescription = "Clear", tint = TextSecondary)
                            }
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = SurgicalCyan,
                        unfocusedBorderColor = BorderSubtle,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        focusedContainerColor = NavySurface,
                        unfocusedContainerColor = NavySurface
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth().testTag("drug_search_input"),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                // 12 Structured Drug Category Filter Chips
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth().testTag("drug_category_filter_row")
                ) {
                    item {
                        FilterChip(
                            selected = selectedCategoryFilter == null,
                            onClick = { selectedCategoryFilter = null },
                            label = { Text("All (${allDrugs.size})", style = MaterialTheme.typography.labelSmall) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = SurgicalCyan,
                                selectedLabelColor = DeepObsidian,
                                containerColor = NavySurface,
                                labelColor = TextSecondary
                            )
                        )
                    }
                    items(DrugCategory.values()) { cat ->
                        val count = allDrugs.count { it.category == cat }
                        FilterChip(
                            selected = selectedCategoryFilter == cat,
                            onClick = {
                                selectedCategoryFilter = if (selectedCategoryFilter == cat) null else cat
                            },
                            label = { Text("${cat.displayName} ($count)", style = MaterialTheme.typography.labelSmall) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = SurgicalCyan,
                                selectedLabelColor = DeepObsidian,
                                containerColor = NavySurface,
                                labelColor = TextSecondary
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredDrugs, key = { it.id }) { drug ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = NavySurface),
                            border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { onNavigateToDrug(drug.id) }
                                .testTag("drug_card_${drug.id}")
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
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
                                    StatusBadge(status = drug.category.displayName, color = SurgicalCyan)
                                }

                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = drug.mechanismOfAction,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextSecondary,
                                    maxLines = 2,
                                    lineHeight = 16.sp
                                )

                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Target: ${drug.targetReceptor}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = ClinicalTeal,
                                        fontWeight = FontWeight.Medium
                                    )
                                    Text(
                                        text = "View Monograph →",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = SurgicalCyan,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }

                    item { Spacer(modifier = Modifier.height(16.dp)) }
                }
            }
            1 -> {
                // SURGICAL GUIDES SECTION
                OutlinedTextField(
                    value = guideSearchQuery,
                    onValueChange = { guideSearchQuery = it },
                    placeholder = { Text("Search surgical guides by procedure or specialty...", color = TextMuted) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = null, tint = SurgicalCyan)
                    },
                    trailingIcon = {
                        if (guideSearchQuery.isNotBlank()) {
                            IconButton(onClick = { guideSearchQuery = "" }) {
                                Icon(imageVector = Icons.Filled.Close, contentDescription = "Clear", tint = TextSecondary)
                            }
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = SurgicalCyan,
                        unfocusedBorderColor = BorderSubtle,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        focusedContainerColor = NavySurface,
                        unfocusedContainerColor = NavySurface
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth().testTag("guide_search_input"),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                // 11 Core Surgical Specialty Filter Chips
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth().testTag("guide_specialty_filter_row")
                ) {
                    item {
                        FilterChip(
                            selected = selectedSpecialtyFilter == null,
                            onClick = { selectedSpecialtyFilter = null },
                            label = { Text("All (${allGuides.size})", style = MaterialTheme.typography.labelSmall) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = SurgicalCyan,
                                selectedLabelColor = DeepObsidian,
                                containerColor = NavySurface,
                                labelColor = TextSecondary
                            )
                        )
                    }
                    items(SurgicalSpecialty.values()) { specialty ->
                        val count = allGuides.count {
                            it.surgicalSpecialty.contains(specialty.displayName, ignoreCase = true) ||
                                    (specialty == SurgicalSpecialty.EMERGENCY_SURGERY && it.surgicalSpecialty.contains("Emergency", ignoreCase = true))
                        }
                        FilterChip(
                            selected = selectedSpecialtyFilter == specialty,
                            onClick = {
                                selectedSpecialtyFilter = if (selectedSpecialtyFilter == specialty) null else specialty
                            },
                            label = { Text("${specialty.displayName} ($count)", style = MaterialTheme.typography.labelSmall) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = ClinicalTeal,
                                selectedLabelColor = DeepObsidian,
                                containerColor = NavySurface,
                                labelColor = TextSecondary
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (filteredGuides.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Filled.SearchOff,
                                contentDescription = null,
                                tint = TextSecondary,
                                modifier = Modifier.size(36.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("No surgical guides match the filter criteria", color = TextSecondary, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(filteredGuides, key = { it.id }) { guide ->
                            Card(
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(containerColor = NavySurface),
                                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(14.dp))
                                    .clickable { onNavigateToGuide(guide.id) }
                                    .testTag("guide_card_${guide.id}")
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        StatusBadge(status = guide.surgicalSpecialty, color = ClinicalTeal)
                                        StatusBadge(status = guide.reviewStatus, color = SurgicalCyan)
                                    }

                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = guide.procedureName,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = guide.overview,
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
                                            text = "14 Standard Clinical Sections",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = TextMuted
                                        )
                                        Text(
                                            text = "Open Protocol →",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = SurgicalCyan,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            }
                        }

                        item { Spacer(modifier = Modifier.height(16.dp)) }
                    }
                }
            }
            2 -> {
                // EQUIPMENT & MONITORING SECTION
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(allEquipment, key = { it.id }) { equip ->
                        Card(
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = NavySurface),
                            border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(14.dp))
                                .clickable { onNavigateToEquipment(equip.id) }
                                .testTag("equip_card_${equip.id}")
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                StatusBadge(status = equip.category, color = ElectricBlue)

                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = equip.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )

                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = equip.description,
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
                                        text = "Physics, Failures & Waveforms",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = TextMuted
                                    )
                                    Text(
                                        text = "Inspect Details →",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = SurgicalCyan,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }

                    item { Spacer(modifier = Modifier.height(16.dp)) }
                }
            }
        }
    }
}
