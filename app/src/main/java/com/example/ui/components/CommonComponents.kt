package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.LearningPathway
import com.example.ui.theme.*
import com.example.ui.viewmodel.NavigationDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnesthesiaTopBar(
    title: String,
    currentPathway: LearningPathway,
    canNavigateBack: Boolean,
    onNavigateBack: () -> Unit,
    onPathwayClick: () -> Unit,
    onSearchClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onSettingsClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Offline Clinical & Academic Platform",
                    style = MaterialTheme.typography.labelSmall,
                    color = TextSecondary
                )
            }
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier.testTag("nav_back_button").minimumInteractiveComponentSize()
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = SurgicalCyan
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .padding(start = 12.dp, end = 8.dp)
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(NavySurfaceVariant)
                        .border(1.dp, SurgicalCyan.copy(alpha = 0.5f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocalHospital,
                        contentDescription = "Anesthesia Pro Icon",
                        tint = SurgicalCyan,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        },
        actions = {
            // Pathway badge
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = NavySurfaceVariant,
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier
                    .padding(end = 4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onPathwayClick() }
                    .testTag("pathway_badge")
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(
                                if (currentPathway == LearningPathway.NURSE_ANESTHESIA) ClinicalTeal else SurgicalCyan
                            )
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = if (currentPathway == LearningPathway.NURSE_ANESTHESIA) "Nurse Anesthesia" else "Physician Anesth",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            IconButton(
                onClick = onSearchClick,
                modifier = Modifier.testTag("search_button").minimumInteractiveComponentSize()
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = TextPrimary
                )
            }

            IconButton(
                onClick = onBookmarksClick,
                modifier = Modifier.testTag("bookmarks_button").minimumInteractiveComponentSize()
            ) {
                Icon(
                    imageVector = Icons.Filled.BookmarkBorder,
                    contentDescription = "Saved Items & Notes",
                    tint = TextPrimary
                )
            }

            IconButton(
                onClick = onSettingsClick,
                modifier = Modifier.testTag("topbar_settings_button").minimumInteractiveComponentSize()
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings",
                    tint = TextPrimary
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = DeepObsidian
        )
    )
}

@Composable
fun AnesthesiaBottomNavigation(
    currentDestination: NavigationDestination,
    onTabSelected: (NavigationDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = NavySurface,
        tonalElevation = 8.dp,
        modifier = modifier.windowInsetsPadding(WindowInsets.navigationBars)
    ) {
        val items = listOf(
            Triple(NavigationDestination.Dashboard, "Home", Icons.Filled.Home),
            Triple(NavigationDestination.Curriculum, "Curriculum", Icons.Filled.MenuBook),
            Triple(NavigationDestination.Pharmacology, "Drugs & Ref", Icons.Filled.Medication),
            Triple(NavigationDestination.Calculators, "Calculators", Icons.Filled.Calculate),
            Triple(NavigationDestination.SimAndQuiz, "Sim & Quiz", Icons.Filled.MonitorHeart)
        )

        items.forEach { (dest, label, icon) ->
            val selected = when (currentDestination) {
                is NavigationDestination.Dashboard -> dest == NavigationDestination.Dashboard
                is NavigationDestination.Curriculum, is NavigationDestination.LessonDetail -> dest == NavigationDestination.Curriculum
                is NavigationDestination.Pharmacology, is NavigationDestination.DrugDetail,
                is NavigationDestination.SurgicalGuideDetail, is NavigationDestination.EquipmentDetail -> dest == NavigationDestination.Pharmacology
                is NavigationDestination.Calculators -> dest == NavigationDestination.Calculators
                is NavigationDestination.SimAndQuiz, is NavigationDestination.CaseSimDetail -> dest == NavigationDestination.SimAndQuiz
                else -> false
            }

            NavigationBarItem(
                selected = selected,
                onClick = { onTabSelected(dest) },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = DeepObsidian,
                    selectedTextColor = SurgicalCyan,
                    indicatorColor = SurgicalCyan,
                    unselectedIconColor = TextSecondary,
                    unselectedTextColor = TextSecondary
                ),
                modifier = Modifier.testTag("nav_item_${label.lowercase().replace(" ", "_")}")
            )
        }
    }
}

@Composable
fun StatusBadge(
    status: String,
    modifier: Modifier = Modifier,
    color: Color = ClinicalTeal
) {
    Surface(
        shape = RoundedCornerShape(4.dp),
        color = color.copy(alpha = 0.15f),
        border = androidx.compose.foundation.BorderStroke(0.5.dp, color.copy(alpha = 0.5f)),
        modifier = modifier
    ) {
        Text(
            text = status,
            style = MaterialTheme.typography.labelSmall,
            color = color,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            fontSize = 10.sp
        )
    }
}

@Composable
fun MedicalSectionHeader(
    title: String,
    subtitle: String? = null,
    icon: ImageVector? = null,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = SurgicalCyan,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.labelSmall,
                        color = TextSecondary
                    )
                }
            }
        }

        if (actionText != null && onActionClick != null) {
            TextButton(
                onClick = onActionClick,
                modifier = Modifier.minimumInteractiveComponentSize()
            ) {
                Text(
                    text = actionText,
                    style = MaterialTheme.typography.labelMedium,
                    color = SurgicalCyan,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun OfflineNoticeCard(modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = NavySurfaceVariant.copy(alpha = 0.6f),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.WifiOff,
                contentDescription = "Offline Ready",
                tint = ClinicalTeal,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "100% Offline Medical Reference",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "All curriculum, drug dosages, surgical plans, and calculators run on-device without internet.",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
        }
    }
}
