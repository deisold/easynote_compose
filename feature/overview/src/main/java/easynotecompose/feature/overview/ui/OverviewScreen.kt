package easynotecompose.feature.overview.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dirkeisold.easynotecompose.core.model.Note
import com.dirkeisold.easynotecompose.core.ui.common.DevicePreviews
import com.dirkeisold.easynotecompose.core.ui.common.OnLifecycleEvent
import com.dirkeisold.easynotecompose.core.ui.common.case
import com.dirkeisold.easynotecompose.core.ui.components.LoadingScreen
import com.dirkeisold.easynotecompose.design.component.AppBackground
import com.dirkeisold.easynotecompose.design.theme.MyTheme
import org.koin.androidx.compose.koinViewModel
import java.util.Date

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun OverviewListRoute(
    modifier: Modifier = Modifier,
    viewModel: OverviewViewModel = koinViewModel(),
    navigateToDetails: (String) -> Unit,
    navigateToCreate: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    OnLifecycleEvent { _, event ->
        event.case(Lifecycle.Event.ON_RESUME) { viewModel.onResume() }
    }
    OverviewScreen(
        uiState = uiState, modifier = modifier,
        navigateToDetails = navigateToDetails,
        navigateToCreate = navigateToCreate
    )
}

@Composable
internal fun OverviewScreen(
    uiState: OverviewViewModel.OverviewUiState,
    modifier: Modifier = Modifier,
    navigateToDetails: (String) -> Unit,
    navigateToCreate: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            when (uiState) {
                OverviewViewModel.OverviewUiState.Loading -> LoadingScreen(modifier)
                is OverviewViewModel.OverviewUiState.Data -> OverviewListScreen(
                    modifier = modifier,
                    notes = uiState.notes,
                    navigateToDetails = navigateToDetails
                )

                OverviewViewModel.OverviewUiState.Error -> {
                }
            }
        }
        FloatingActionButton(
            modifier = modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
            onClick = { navigateToCreate() }
        ) {
            Icon(Icons.Filled.Add, "")
        }
    }
}


@DevicePreviews
@Composable
internal fun OverviewScreenPreview() {
    MyTheme {
        AppBackground {
            OverviewScreen(
                uiState = OverviewViewModel.OverviewUiState.Data(
                    notes = MutableList(10, init = { index -> createRandomNote(index) })
                ),
                navigateToDetails = {},
                navigateToCreate = {}
            )
        }
    }
}

internal fun createRandomNote(index: Int) = Note(
    id = (index + 1).toString(),
    created = Date(),
    title = "Note #${index + 1}",
    text = "Text #${index + 1}"
)

@Preview
@Composable
internal fun OverviewScreenLoadingPreview() {
    MyTheme {
        AppBackground {
            OverviewScreen(
                uiState = OverviewViewModel.OverviewUiState.Loading,
                navigateToDetails = {},
                navigateToCreate = {}
            )
        }
    }
}
