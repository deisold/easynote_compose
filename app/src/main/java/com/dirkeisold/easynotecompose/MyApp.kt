package com.dirkeisold.easynotecompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dirkeisold.easynotecompose.design.component.AppBackground
import easynotecompose.feature.details.navigation.createNoteScreen
import easynotecompose.feature.details.navigation.detailsScreen
import easynotecompose.feature.details.navigation.navigateToCreateNote
import easynotecompose.feature.details.navigation.navigateToDetailsFor
import easynotecompose.feature.overview.navigation.overviewNavigationRoute
import easynotecompose.feature.overview.navigation.overviewScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MyApp() {
    val navController: NavHostController = rememberNavController()

    AppBackground {
        Scaffold(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
        ) { padding ->
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumedWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal
                        )
                    )
            ) {
                Column(Modifier.fillMaxSize()) {
                    NavHost(
                        navController = navController,
                        startDestination = overviewNavigationRoute,
                        modifier = Modifier,
                    ) {
                        overviewScreen(
                            navigateToDetails = { noteId ->
                                navController.navigateToDetailsFor(noteId)
                            },
                            navigateToCreate = {
                                navController.navigateToCreateNote()
                            },
                            nestedGraph = {
                                detailsScreen { navController.popBackStack() }
                                createNoteScreen { navController.popBackStack() }
                            }
                        )
                    }
                }
            }
        }
    }
}