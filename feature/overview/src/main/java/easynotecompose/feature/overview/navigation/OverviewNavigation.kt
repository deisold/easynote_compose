package easynotecompose.feature.overview.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import easynotecompose.feature.overview.ui.OverviewListRoute

const val overviewNavigationRoute = "for_you_route"

fun NavController.navigateToOverview(navOptions: NavOptions? = null) {
    navigate(overviewNavigationRoute)
}

fun NavGraphBuilder.overviewScreen() {
    composable(route = overviewNavigationRoute) {
        OverviewListRoute()
    }
}