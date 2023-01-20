package easynotecompose.feature.details.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dirkeisold.easynotecompose.core.model.Note
import com.dirkeisold.easynotecompose.core.repository.NoteRepository
import com.dirkeisold.easynotecompose.core.ui.common.MviViewModel
import com.dirkeisold.easynotecompose.core.ui.common.UiState
import easynotecompose.feature.details.navigation.DetailsArgs
import kotlinx.coroutines.launch

class DetailsViewModel(
    val noteRepository: NoteRepository,
    val savedStateHandle: SavedStateHandle,
) : MviViewModel<DetailsViewModel.DetailsUiState>() {
    sealed interface DetailsUiState : UiState {
        object Loading : DetailsUiState
        object Error : DetailsUiState
        data class Data(val note: Note) : DetailsUiState
    }

    private val args = DetailsArgs(savedStateHandle)

    override fun getInitialUiState() = DetailsUiState.Loading

    init {
        loadNote()
    }

    private fun loadNote() {
        viewModelScope.launch {
            noteRepository.get(args.noteId).fold(
                onFailure = { state(DetailsUiState.Error) },
                onSuccess = {
                    state(it?.let { DetailsUiState.Data(it) } ?: DetailsUiState.Error)
                }
            )
        }
    }
}