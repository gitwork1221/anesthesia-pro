package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.data.model.AnatomySystemCategory
import com.example.data.model.AnatomyTopic
import com.example.data.model.AssetVerificationStatus
import com.example.data.repository.anatomy.AnatomyRepository
import com.example.ui.components.StatusBadge
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnatomyViewerScreen(
    onNavigateBack: () -> Unit,
    onNavigateToGuide: (String) -> Unit = {},
    initialTopicId: String? = null,
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf<AnatomySystemCategory?>(null) }
    val filteredTopics = remember(selectedCategory) {
        AnatomyRepository.getTopicsByCategory(selectedCategory)
    }

    var selectedTopicId by remember {
        mutableStateOf(initialTopicId ?: filteredTopics.firstOrNull()?.id ?: AnatomyRepository.allTopics.first().id)
    }

    // Keep selectedTopic updated if category changes and current topic not in list
    val currentTopic = remember(selectedTopicId, filteredTopics) {
        filteredTopics.find { it.id == selectedTopicId }
            ?: filteredTopics.firstOrNull()
            ?: AnatomyRepository.allTopics.first()
    }

    var selectedStructure by remember(currentTopic.id) {
        mutableStateOf<AnatomicalStructure?>(currentTopic.structures.firstOrNull())
    }

    var selectedContentTab by remember { mutableIntStateOf(0) }
    var showProvenanceDialog by remember { mutableStateOf(false) }
    var show3DRoadmapDialog by remember { mutableStateOf(false) }

    val contentTabs = listOf("Structures", "Physiology", "Exam Pearls", "Self-Assessment", "Surgical Links", "Licensing")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Applied Anatomy & Physiology",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "9 Core Anesthesia Systems • Interactive Landmarks",
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
                        onClick = { show3DRoadmapDialog = true },
                        modifier = Modifier.testTag("btn_3d_roadmap_info")
                    ) {
                        Icon(imageVector = Icons.Default.ViewInAr, contentDescription = "3D Architecture Status", tint = SurgicalCyan)
                    }
                    IconButton(
                        onClick = { showProvenanceDialog = true },
                        modifier = Modifier.testTag("btn_provenance_info")
                    ) {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "Asset Provenance & Licensing", tint = TextSecondary)
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
            contentPadding = PaddingValues(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Mode Indicator Row (2D Active vs 3D Roadmap)
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(NavySurface)
                        .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(6.dp))
                            .background(NavySurfaceVariant)
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.TouchApp, contentDescription = null, tint = SurgicalCyan, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("2D Interactive Landmarks", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = SurgicalCyan)
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(6.dp))
                            .clickable { show3DRoadmapDialog = true }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.ViewInAr, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("3D Engine Readiness", style = MaterialTheme.typography.labelSmall, color = TextSecondary)
                        }
                    }
                }
            }

            // Category Filter Scroll Row
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = selectedCategory == null,
                        onClick = { selectedCategory = null },
                        label = { Text("All (9 Systems)", fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = SurgicalCyan,
                            selectedLabelColor = DeepObsidian,
                            containerColor = NavySurface,
                            labelColor = TextPrimary
                        ),
                        modifier = Modifier.testTag("category_chip_all")
                    )

                    AnatomySystemCategory.entries.forEach { cat ->
                        FilterChip(
                            selected = selectedCategory == cat,
                            onClick = {
                                selectedCategory = cat
                                val topicsInCat = AnatomyRepository.getTopicsByCategory(cat)
                                if (topicsInCat.isNotEmpty()) {
                                    selectedTopicId = topicsInCat.first().id
                                }
                            },
                            label = { Text(cat.displayName, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = SurgicalCyan,
                                selectedLabelColor = DeepObsidian,
                                containerColor = NavySurface,
                                labelColor = TextPrimary
                            ),
                            modifier = Modifier.testTag("category_chip_${cat.name.lowercase()}")
                        )
                    }
                }
            }

            // Topic Switcher Dropdown / Carousel if multiple topics match
            if (filteredTopics.size > 1) {
                item {
                    Column {
                        Text(
                            text = "Select Module Topic (${filteredTopics.size} available):",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            filteredTopics.forEach { topic ->
                                val isSelected = topic.id == currentTopic.id
                                Card(
                                    shape = RoundedCornerShape(8.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = if (isSelected) NavySurfaceVariant else NavySurface
                                    ),
                                    border = androidx.compose.foundation.BorderStroke(
                                        1.dp,
                                        if (isSelected) SurgicalCyan else BorderSubtle
                                    ),
                                    modifier = Modifier
                                        .clickable {
                                            selectedTopicId = topic.id
                                            selectedStructure = topic.structures.firstOrNull()
                                        }
                                        .testTag("topic_selector_${topic.id}")
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = topic.title,
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                            color = if (isSelected) SurgicalCyan else Color.White
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Interactive 2D Canvas with Landmark Pins
            item {
                AnatomyInteractiveCanvas(
                    topic = currentTopic,
                    selectedStructure = selectedStructure,
                    onSelectStructure = { struct ->
                        selectedStructure = struct
                        selectedContentTab = 0 // Auto switch to Structures tab
                    }
                )
            }

            // Objectives Summary Card
            item {
                ObjectivesSection(objectives = currentTopic.learningObjectives)
            }

            // Content Tabs Row
            item {
                ScrollableTabRow(
                    selectedTabIndex = selectedContentTab,
                    containerColor = NavySurface,
                    contentColor = SurgicalCyan,
                    edgePadding = 0.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .testTag("anatomy_content_tabs")
                ) {
                    contentTabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedContentTab == index,
                            onClick = { selectedContentTab = index },
                            text = {
                                Text(
                                    text = title,
                                    fontSize = 12.sp,
                                    fontWeight = if (selectedContentTab == index) FontWeight.Bold else FontWeight.Normal,
                                    color = if (selectedContentTab == index) SurgicalCyan else TextSecondary
                                )
                            }
                        )
                    }
                }
            }

            // Tab Content
            item {
                when (selectedContentTab) {
                    0 -> StructuresSection(
                        structures = currentTopic.structures,
                        selectedStructure = selectedStructure,
                        onSelectStructure = { selectedStructure = it }
                    )
                    1 -> PhysiologyAndAnesthesiaSection(topic = currentTopic)
                    2 -> ExamDistinctionsSection(distinctions = currentTopic.commonExamDistinctions)
                    3 -> ReviewQuizSection(questions = currentTopic.reviewQuestions)
                    4 -> SurgicalLinkageSection(
                        guideIds = currentTopic.relatedSurgicalGuideIds,
                        onNavigateToGuide = onNavigateToGuide
                    )
                    5 -> AssetProvenanceCard(metadata = currentTopic.assetMetadata)
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }

    // Asset Provenance Dialog
    if (showProvenanceDialog) {
        AlertDialog(
            onDismissRequest = { showProvenanceDialog = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Verified, contentDescription = null, tint = SurgicalCyan)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Asset Provenance & License", style = MaterialTheme.typography.titleMedium, color = Color.White)
                }
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Title: ${currentTopic.assetMetadata.assetTitle}", style = MaterialTheme.typography.bodySmall, color = TextPrimary)
                    Text("System: ${currentTopic.assetMetadata.topicName}", style = MaterialTheme.typography.bodySmall, color = TextPrimary)
                    Text("Source Institution: ${currentTopic.assetMetadata.creatorOrInstitution}", style = MaterialTheme.typography.bodySmall, color = TextPrimary)
                    Text("License: ${currentTopic.assetMetadata.licenseAndAttribution}", style = MaterialTheme.typography.bodySmall, color = TextPrimary)
                    Text("Source Reference: ${currentTopic.assetMetadata.sourceUrl}", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                    Text("Audit Status: ${currentTopic.assetMetadata.status.displayName} (${currentTopic.assetMetadata.verificationDate})", style = MaterialTheme.typography.bodySmall, color = MintSuccess)
                    Text("Resolution: ${currentTopic.assetMetadata.intrinsicWidth} x ${currentTopic.assetMetadata.intrinsicHeight} px", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                    Text("Note: ${currentTopic.assetMetadata.statusNote}", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
            },
            confirmButton = {
                TextButton(onClick = { showProvenanceDialog = false }) {
                    Text("Close", color = SurgicalCyan)
                }
            },
            containerColor = NavySurface
        )
    }

    // 3D Engine Architecture & Status Dialog
    if (show3DRoadmapDialog) {
        AlertDialog(
            onDismissRequest = { show3DRoadmapDialog = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.ViewInAr, contentDescription = null, tint = SurgicalCyan)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("3D Engine & Architecture Status", style = MaterialTheme.typography.titleMedium, color = Color.White)
                }
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    StatusBadge(status = "2D INTERACTIVE LANDMARKS ACTIVE", color = MintSuccess)
                    Text(
                        text = AnatomyRepository.THREE_D_PIPELINE_STATUS,
                        style = MaterialTheme.typography.bodySmall,
                        color = TextPrimary,
                        lineHeight = 18.sp
                    )
                    HorizontalDivider(color = BorderSubtle)
                    Text(
                        text = "Technical Prerequisites for Native 3D Viewers:\n" +
                                "• Android Filament / Google Sceneview engine bindings\n" +
                                "• High-fidelity glTF / GLB polygon meshes with UV coordinate unwrapping\n" +
                                "• Touch-to-world camera raycast hit-testing for anatomical structure picking\n" +
                                "• Zero-Mock Protocol: Simulated Canvas rotating animations and non-interactive image captures are strictly rejected.",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextSecondary,
                        lineHeight = 16.sp
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { show3DRoadmapDialog = false }) {
                    Text("Acknowledge", color = SurgicalCyan)
                }
            },
            containerColor = NavySurface
        )
    }
}

@Composable
fun AssetProvenanceCard(metadata: com.example.data.model.AnatomyAssetMetadata) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = Modifier.fillMaxWidth().testTag("asset_provenance_card")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            val statusColor = when (metadata.status) {
                AssetVerificationStatus.CLINICALLY_REVIEWED -> MintSuccess
                AssetVerificationStatus.SOURCE_VERIFIED, AssetVerificationStatus.LICENSE_VERIFIED -> SurgicalCyan
                AssetVerificationStatus.IMPORTED_UNVERIFIED -> WarningYellow
                AssetVerificationStatus.MISSING, AssetVerificationStatus.NEEDS_CORRECTION -> CrimsonDanger
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Verified, contentDescription = null, tint = statusColor, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Asset Provenance & Attribution", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = Color.White)
                }
                StatusBadge(status = metadata.status.displayName.uppercase(), color = statusColor)
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text("Asset Title: ${metadata.assetTitle}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold, color = Color.White)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Domain Topic: ${metadata.topicName}", style = MaterialTheme.typography.bodySmall, color = TextPrimary)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Creator / Institution: ${metadata.creatorOrInstitution}", style = MaterialTheme.typography.bodySmall, color = TextPrimary)
            Spacer(modifier = Modifier.height(4.dp))
            Text("License: ${metadata.licenseAndAttribution}", style = MaterialTheme.typography.bodySmall, color = TextPrimary)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Source Reference: ${metadata.sourceUrl}", style = MaterialTheme.typography.bodySmall, color = SurgicalCyan)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Calibration Resolution: ${metadata.intrinsicWidth} x ${metadata.intrinsicHeight} px", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Audit Status: ${metadata.statusNote} (${metadata.verificationDate})", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
        }
    }
}
