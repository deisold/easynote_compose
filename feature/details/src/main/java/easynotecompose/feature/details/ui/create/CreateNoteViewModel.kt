package easynotecompose.feature.details.ui.create

import androidx.lifecycle.viewModelScope
import com.dirkeisold.easynotecompose.core.repository.NoteRepository
import com.dirkeisold.easynotecompose.core.ui.common.MviViewModel
import com.dirkeisold.easynotecompose.core.ui.common.UiAction
import com.dirkeisold.easynotecompose.core.ui.common.UiEvent
import com.dirkeisold.easynotecompose.core.ui.common.UiState
import com.dirkeisold.easynotecompose.core.ui.common.case
import easynotecompose.feature.details.model.UpdatedNote
import kotlinx.coroutines.launch

class CreateNoteViewModel(
    val noteRepository: NoteRepository,
) : MviViewModel<CreateNoteViewModel.CreateNoteUiState, CreateNoteViewModel.CreateNoteUiAction, CreateNoteViewModel.CreateNoteUiEvent>() {
    sealed interface CreateNoteUiState : UiState {
        object Create : CreateNoteUiState
    }

    sealed interface CreateNoteUiAction : UiAction {
        data class OnSaveNote(val note: UpdatedNote) : CreateNoteUiAction
    }

    sealed interface CreateNoteUiEvent : UiEvent {
        object Saved : CreateNoteUiEvent
        object ErrorSaving : CreateNoteUiEvent
    }

    override fun getInitialUiState() = CreateNoteUiState.Create

    override fun onUiAction(action: CreateNoteUiAction) {
        when (action) {
            is CreateNoteUiAction.OnSaveNote -> uiState.value.case<CreateNoteUiState.Create> {
                action.note.title.takeIf { it.isNotBlank() }
                    ?.let {
                        createNote(title = it, text = action.note.text)
                    }
            }
        }
    }

    private fun createNote(title: String, text: String?) {
        viewModelScope.launch {
            runCatching { noteRepository.create(title = title, text = text) }.fold(
                onFailure = { event(CreateNoteUiEvent.ErrorSaving) },
                onSuccess = { event(CreateNoteUiEvent.Saved) }
            )
        }
    }
}