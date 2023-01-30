package easynotecompose.feature.details.ui.create

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dirkeisold.easynotecompose.core.ui.extension.CollectAsEffect
import easynotecompose.feature.details.model.UpdatedNote
import easynotecompose.feature.details.ui.views.DetailsEditView
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun CreateNoteRoute(
    modifier: Modifier = Modifier,
    viewModel: CreateNoteViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CreateNoteScreen(
        uiState = uiState,
        modifier = modifier,
        onBackClick = onBackClick,
        onSaveClicked = { note ->
            viewModel.onUiAction(CreateNoteViewModel.CreateNoteUiAction.OnSaveNote(note))
        },
    )

    val context = LocalContext.current
    viewModel.uiEvent.CollectAsEffect {
        Log.d("CreateNoteRoute", "uiEvent=$it")

        when (it) {
            CreateNoteViewModel.CreateNoteUiEvent.ErrorSaving -> {
                Toast.makeText(context, "Error saving note", Toast.LENGTH_LONG).show()
            }

            CreateNoteViewModel.CreateNoteUiEvent.Saved -> {
                onBackClick()
            }
        }
    }
}

@Composable
fun CreateNoteScreen(
    uiState: CreateNoteViewModel.CreateNoteUiState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSaveClicked: (UpdatedNote) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        when (uiState) {
            is CreateNoteViewModel.CreateNoteUiState.Create -> DetailsEditView(
                modifier = modifier,
                title = "",
                text = "",
                onBackClick = onBackClick,
                onSaveClicked = onSaveClicked
            )
        }
    }
}