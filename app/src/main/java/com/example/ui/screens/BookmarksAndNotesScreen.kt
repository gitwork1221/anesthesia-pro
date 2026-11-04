package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.ui.components.StatusBadge
import com.example.ui.theme.*
import com.example.ui.viewmodel.AnesthesiaViewModel
import com.example.ui.viewmodel.NavigationDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksAndNotesScreen(
    viewModel: AnesthesiaViewModel,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Bookmarks, 1: Study Notes
    val bookmarks by viewModel.bookmarks.collectAsState()
    val notes by viewModel.allNotes.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Saved References & Notes",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack, modifier = Modifier.minimumInteractiveComponentSize()) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = SurgicalCyan)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DeepObsidian)
            )
        },
        containerColor = DeepObsidian,
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            SecondaryTabRow(
                selectedTabIndex = selectedTab,
                containerColor = NavySurface,
                contentColor = SurgicalCyan,
                divider = { HorizontalDivider(color = BorderSubtle) }
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Filled.Bookmark, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Bookmarks (${bookmarks.size})", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Filled.NoteAlt, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Personal Notes (${notes.size})", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (selectedTab == 0) {
                if (bookmarks.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(imageVector = Icons.Filled.BookmarkBorder, contentDescription = null, tint = TextMuted, modifier = Modifier.size(52.dp))
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("No bookmarked topics yet", style = MaterialTheme.typography.titleSmall, color = TextSecondary)
                            Text("Tap the bookmark icon on any lesson, drug, or guide to pin it here.", style = MaterialTheme.typography.bodySmall, color = TextMuted)
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(bookmarks, key = { it.id }) { bm ->
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = NavySurface),
                                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .clickable {
                                        when (bm.type) {
                                            "lesson" -> viewModel.navigateTo(NavigationDestination.LessonDetail(bm.id))
                                            "drug" -> viewModel.navigateTo(NavigationDestination.DrugDetail(bm.id))
                                            "guide" -> viewModel.navigateTo(NavigationDestination.SurgicalGuideDetail(bm.id))
                                            "equipment" -> viewModel.navigateTo(NavigationDestination.EquipmentDetail(bm.id))
                                        }
                                    }
                            ) {
                                Row(
                                    modifier = Modifier.padding(14.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        StatusBadge(status = bm.type.uppercase(), color = SurgicalCyan)
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(text = bm.title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                        Text(text = bm.subtitle, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                                    }
                                    IconButton(
                                        onClick = { viewModel.toggleBookmark(bm.id, bm.type, bm.title, bm.subtitle) },
                                        modifier = Modifier.minimumInteractiveComponentSize()
                                    ) {
                                        Icon(imageVector = Icons.Filled.Bookmark, contentDescription = "Remove Bookmark", tint = AlertAmber)
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (notes.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(imageVector = Icons.Filled.EditNote, contentDescription = null, tint = TextMuted, modifier = Modifier.size(52.dp))
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("No study notes written yet", style = MaterialTheme.typography.titleSmall, color = TextSecondary)
                            Text("Open any lesson to jot down quick clinical pearls or exam tips.", style = MaterialTheme.typography.bodySmall, color = TextMuted)
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(notes, key = { it.id }) { note ->
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = NavySurface),
                                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = note.targetTitle,
                                            style = MaterialTheme.typography.titleSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = SurgicalCyan
                                        )
                                        IconButton(
                                            onClick = { viewModel.deleteNote(note.id) },
                                            modifier = Modifier.size(28.dp).minimumInteractiveComponentSize()
                                        ) {
                                            Icon(imageVector = Icons.Filled.DeleteOutline, contentDescription = "Delete", tint = AlarmRose)
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = note.content,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = TextPrimary,
                                        lineHeight = 20.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
