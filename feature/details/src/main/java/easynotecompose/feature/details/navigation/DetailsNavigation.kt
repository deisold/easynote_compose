package easynotecompose.feature.details.navigation

import android.net.Uri
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import easynotecompose.feature.details.ui.details.DetailsRoute

const val detailsNavigationRoute = "details_route"

@VisibleForTesting
internal const val noteIdArg = "noteId"

internal class DetailsArgs(val noteId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(Uri.decode(checkNotNull(savedStateHandle[noteIdArg])))
}

fun NavController.navigateToDetailsFor(noteId: String) {
    val encodedId = Uri.encode(noteId)
    navigate("$detailsNavigationRoute/$encodedId")
}

fun NavGraphBuilder.detailsScreen(onBackClick: () -> Unit) {
    composable(
        route = "$detailsNavigationRoute/{$noteIdArg}",
        arguments = listOf(navArgument(noteIdArg) { type = NavType.StringType })
    ) {
        DetailsRoute(onBackClick = onBackClick)
    }
}