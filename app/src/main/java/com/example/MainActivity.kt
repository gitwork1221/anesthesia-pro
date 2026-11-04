package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.AnesthesiaBottomNavigation
import com.example.ui.components.AnesthesiaTopBar
import com.example.ui.screens.*
import com.example.ui.theme.AnesthesiaProTheme
import com.example.ui.viewmodel.AnesthesiaViewModel
import com.example.ui.viewmodel.NavigationDestination

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: AnesthesiaViewModel = viewModel()
            val themeMode by viewModel.themeMode.collectAsState()
            AnesthesiaProTheme(themeMode = themeMode) {
                AnesthesiaProApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun AnesthesiaProApp(viewModel: AnesthesiaViewModel) {
    val currentDestination by viewModel.currentDestination.collectAsState()
    val pathway by viewModel.pathway.collectAsState()

    var showPathwayDialog by remember { mutableStateOf(false) }
    var showSearchDialog by remember { mutableStateOf(false) }

    val canNavigateBack = when (currentDestination) {
        NavigationDestination.Dashboard,
        NavigationDestination.Curriculum,
        NavigationDestination.Pharmacology,
        NavigationDestination.Calculators,
        NavigationDestination.SimAndQuiz -> false
        else -> true
    }

    BackHandler(enabled = canNavigateBack) {
        viewModel.popBack()
    }

    if (showPathwayDialog) {
        PathwayDialog(
            viewModel = viewModel,
            onDismiss = { showPathwayDialog = false }
        )
    }

    if (showSearchDialog) {
        GlobalSearchDialog(
            viewModel = viewModel,
            onDismiss = { showSearchDialog = false }
        )
    }

    Scaffold(
        topBar = {
            val title = when (currentDestination) {
                NavigationDestination.Dashboard -> "Anesthesia Pro"
                NavigationDestination.Curriculum -> "Curriculum"
                NavigationDestination.Pharmacology -> "Pharmacology & Reference"
                NavigationDestination.Calculators -> "Calculators"
                NavigationDestination.SimAndQuiz -> "Sim & Exam Practice"
                NavigationDestination.AnatomyViewer -> "Applied Anatomy & Physiology"
                NavigationDestination.BookmarksAndNotes -> "Saved Notes & Topics"
                NavigationDestination.Settings -> "Settings & System"
                is NavigationDestination.LessonDetail -> "Lesson Reader"
                is NavigationDestination.DrugDetail -> "Drug Monograph"
                is NavigationDestination.SurgicalGuideDetail -> "Surgical Protocol"
                is NavigationDestination.EquipmentDetail -> "Equipment Topic"
                is NavigationDestination.CaseSimDetail -> "Clinical Simulation"
            }

            AnesthesiaTopBar(
                title = title,
                currentPathway = pathway,
                canNavigateBack = canNavigateBack,
                onNavigateBack = { viewModel.popBack() },
                onPathwayClick = { showPathwayDialog = true },
                onSearchClick = { showSearchDialog = true },
                onBookmarksClick = { viewModel.navigateTo(NavigationDestination.BookmarksAndNotes) },
                onSettingsClick = { viewModel.navigateTo(NavigationDestination.Settings) }
            )
        },
        bottomBar = {
            AnesthesiaBottomNavigation(
                currentDestination = currentDestination,
                onTabSelected = { dest -> viewModel.selectBottomTab(dest) }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Crossfade(targetState = currentDestination, label = "screen_crossfade") { dest ->
                when (dest) {
                    NavigationDestination.Dashboard -> {
                        DashboardScreen(
                            viewModel = viewModel,
                            onNavigateToLesson = { lessonId ->
                                viewModel.navigateTo(NavigationDestination.LessonDetail(lessonId))
                            },
                            onNavigateToCase = { caseId ->
                                viewModel.startCaseSimulation(caseId)
                            },
                            onNavigateToAnatomy = {
                                viewModel.navigateTo(NavigationDestination.AnatomyViewer)
                            },
                            onOpenPathwayDialog = { showPathwayDialog = true }
                        )
                    }
                    NavigationDestination.Curriculum -> {
                        CurriculumScreen(
                            viewModel = viewModel,
                            onNavigateToLesson = { lessonId ->
                                viewModel.navigateTo(NavigationDestination.LessonDetail(lessonId))
                            }
                        )
                    }
                    NavigationDestination.Pharmacology -> {
                        PharmacologyAndReferenceScreen(
                            viewModel = viewModel,
                            onNavigateToDrug = { drugId ->
                                viewModel.navigateTo(NavigationDestination.DrugDetail(drugId))
                            },
                            onNavigateToGuide = { guideId ->
                                viewModel.navigateTo(NavigationDestination.SurgicalGuideDetail(guideId))
                            },
                            onNavigateToEquipment = { equipId ->
                                viewModel.navigateTo(NavigationDestination.EquipmentDetail(equipId))
                            }
                        )
                    }
                    NavigationDestination.Calculators -> {
                        CalculatorsScreen()
                    }
                    NavigationDestination.SimAndQuiz -> {
                        SimAndQuizScreen(
                            viewModel = viewModel,
                            onNavigateToCase = { caseId ->
                                viewModel.startCaseSimulation(caseId)
                            }
                        )
                    }
                    NavigationDestination.AnatomyViewer -> {
                        AnatomyViewerScreen(
                            onNavigateBack = { viewModel.popBack() },
                            onNavigateToGuide = { guideId ->
                                viewModel.navigateTo(NavigationDestination.SurgicalGuideDetail(guideId))
                            }
                        )
                    }
                    NavigationDestination.BookmarksAndNotes -> {
                        BookmarksAndNotesScreen(
                            viewModel = viewModel,
                            onNavigateBack = { viewModel.popBack() }
                        )
                    }
                    NavigationDestination.Settings -> {
                        SettingsScreen(
                            viewModel = viewModel,
                            onNavigateBack = { viewModel.popBack() }
                        )
                    }
                    is NavigationDestination.LessonDetail -> {
                        LessonDetailScreen(
                            lessonId = dest.lessonId,
                            viewModel = viewModel,
                            onNavigateBack = { viewModel.popBack() }
                        )
                    }
                    is NavigationDestination.DrugDetail -> {
                        DrugDetailScreen(
                            drugId = dest.drugId,
                            viewModel = viewModel,
                            onNavigateBack = { viewModel.popBack() }
                        )
                    }
                    is NavigationDestination.SurgicalGuideDetail -> {
                        SurgicalGuideDetailScreen(
                            guideId = dest.guideId,
                            viewModel = viewModel,
                            onNavigateBack = { viewModel.popBack() },
                            onNavigateToAnatomy = { _ -> viewModel.navigateTo(NavigationDestination.AnatomyViewer) },
                            onNavigateToDrug = { drugId -> viewModel.navigateTo(NavigationDestination.DrugDetail(drugId)) },
                            onNavigateToEquipment = { equipId -> viewModel.navigateTo(NavigationDestination.EquipmentDetail(equipId)) },
                            onNavigateToCalculator = { viewModel.navigateTo(NavigationDestination.Calculators) },
                            onNavigateToCase = { caseId -> viewModel.startCaseSimulation(caseId) }
                        )
                    }
                    is NavigationDestination.EquipmentDetail -> {
                        EquipmentDetailScreen(
                            equipId = dest.equipId,
                            viewModel = viewModel,
                            onNavigateBack = { viewModel.popBack() }
                        )
                    }
                    is NavigationDestination.CaseSimDetail -> {
                        CaseSimDetailScreen(
                            caseId = dest.caseId,
                            viewModel = viewModel,
                            onNavigateBack = { viewModel.popBack() }
                        )
                    }
                }
            }
        }
    }
}
