package easynotecompose.feature.overview.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import easynotecompose.feature.overview.ui.OverviewListRoute

const val overviewNavigationRoute = "overview_route"

fun NavController.navigateToOverview(navOptions: NavOptions? = null) {
    navigate(overviewNavigationRoute, navOptions)
}

fun NavGraphBuilder.overviewScreen(
    navigateToDetails: (String) -> Unit,
    navigateToCreate: () -> Unit,
    nestedGraph: NavGraphBuilder.() -> Unit
) {
    composable(route = overviewNavigationRoute) {
        OverviewListRoute(
            navigateToDetails = navigateToDetails,
            navigateToCreate = navigateToCreate
        )
    }
    nestedGraph()
}