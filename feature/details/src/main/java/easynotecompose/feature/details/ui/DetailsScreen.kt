package easynotecompose.feature.details.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dirkeisold.easynotecompose.core.ui.components.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun DetailsRoute(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DetailsScreen(uiState = uiState, modifier = modifier)
}

@Composable
fun DetailsScreen(
    uiState: DetailsViewModel.DetailsUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        when (uiState) {
            DetailsViewModel.DetailsUiState.Loading -> LoadingScreen(modifier)
            is DetailsViewModel.DetailsUiState.Data -> DetailsView(
                note = uiState.note,
                modifier = modifier
            )

            DetailsViewModel.DetailsUiState.Error -> TODO()
        }
    }
}