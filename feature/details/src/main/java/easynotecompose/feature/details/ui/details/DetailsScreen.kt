package easynotecompose.feature.details.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dirkeisold.easynotecompose.core.ui.components.LoadingScreen
import easynotecompose.feature.details.model.UpdatedNote
import easynotecompose.feature.details.ui.views.DetailsEditView
import easynotecompose.feature.details.ui.views.DetailsView
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun DetailsRoute(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DetailsScreen(
        uiState = uiState,
        modifier = modifier,
        onBackClick = onBackClick,
        onEditClicked = { viewModel.onUiAction(DetailsViewModel.DetailsUiAction.OnEditNote) },
        onSaveClicked = { note ->
            viewModel.onUiAction(DetailsViewModel.DetailsUiAction.OnSaveNote(note))
        },
    )
}

@Composable
fun DetailsScreen(
    uiState: DetailsViewModel.DetailsUiState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onEditClicked: () -> Unit,
    onSaveClicked: (UpdatedNote) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        when (uiState) {
            DetailsViewModel.DetailsUiState.Loading -> LoadingScreen(modifier)
            is DetailsViewModel.DetailsUiState.Show -> DetailsView(
                modifier = modifier,
                state = uiState,
                onBackClick = onBackClick,
                onEditClicked = onEditClicked
            )

            is DetailsViewModel.DetailsUiState.Edit -> DetailsEditView(
                modifier = modifier,
                title = uiState.title,
                text = uiState.text,
                onBackClick = onBackClick,
                onSaveClicked = onSaveClicked
            )

            DetailsViewModel.DetailsUiState.Error -> TODO()
        }
    }
}