package easynotecompose.feature.details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import easynotecompose.feature.details.ui.create.CreateNoteRoute

const val createNoteNavigationRoute = "create_note_route"

fun NavController.navigateToCreateNote(navOptions: NavOptions? = null) {
    navigate(createNoteNavigationRoute, navOptions)
}

fun NavGraphBuilder.createNoteScreen(onBackClick: () -> Unit) {
    composable(route = createNoteNavigationRoute) {
        CreateNoteRoute(onBackClick = onBackClick)
    }
}