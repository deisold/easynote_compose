package easynotecompose.feature.details.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dirkeisold.easynotecompose.core.model.Note
import com.dirkeisold.easynotecompose.core.repository.NoteRepository
import com.dirkeisold.easynotecompose.core.ui.common.MviViewModel
import com.dirkeisold.easynotecompose.core.ui.common.UiAction
import com.dirkeisold.easynotecompose.core.ui.common.UiEvent
import com.dirkeisold.easynotecompose.core.ui.common.UiState
import com.dirkeisold.easynotecompose.core.ui.common.case
import easynotecompose.feature.details.model.UpdatedNote
import easynotecompose.feature.details.navigation.DetailsArgs
import kotlinx.coroutines.launch

class DetailsViewModel(
    val noteRepository: NoteRepository,
    val savedStateHandle: SavedStateHandle,
) : MviViewModel<DetailsViewModel.DetailsUiState, DetailsViewModel.DetailsUiAction, UiEvent>() {
    sealed interface DetailsUiState : UiState {
        object Loading : DetailsUiState
        object Error : DetailsUiState
        data class Show(val title: String, val text: String?) : DetailsUiState
        data class Edit(val title: String, val text: String?) : DetailsUiState
    }

    sealed interface DetailsUiAction : UiAction {
        object OnEditNote : DetailsUiAction
        data class OnSaveNote(val note: UpdatedNote) : DetailsUiAction
    }

    private val args = DetailsArgs(savedStateHandle)
    private var note: Note? = null
    override fun getInitialUiState() = DetailsUiState.Loading

    init {
        loadNote()
    }

    override fun onUiAction(action: DetailsUiAction) {
        when (action) {
            DetailsUiAction.OnEditNote -> uiState.value.case<DetailsUiState.Show> {
                state(
                    DetailsUiState.Edit(
                        title = this.title,
                        text = this.text
                    )
                )
            }

            is DetailsUiAction.OnSaveNote -> uiState.value.case<DetailsUiState.Edit> {
                updateNote(title = action.note.title, text = action.note.text)
                state(
                    DetailsUiState.Show(
                        title = action.note.title,
                        text = action.note.text
                    )
                )
            }
        }
    }

    private fun loadNote() {
        viewModelScope.launch {
            noteRepository.get(args.noteId).fold(
                onFailure = { state(DetailsUiState.Error) },
                onSuccess = {
                    note = it
                    state(
                        it?.let {
                            DetailsUiState.Show(title = it.title, text = it.text)
                        }
                            ?: DetailsUiState.Error
                    )
                }
            )
        }
    }

    private fun updateNote(title: String, text: String?) {
        note = note?.copy(title = title, text = text)

        note?.let {
            viewModelScope.launch {
                noteRepository.save(it)
            }
        }
    }
}