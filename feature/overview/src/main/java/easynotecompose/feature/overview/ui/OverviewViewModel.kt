package easynotecompose.feature.overview.ui

import androidx.lifecycle.viewModelScope
import com.dirkeisold.easynotecompose.core.model.Note
import com.dirkeisold.easynotecompose.core.repository.NoteRepository
import com.dirkeisold.easynotecompose.core.ui.common.MviViewModel
import com.dirkeisold.easynotecompose.core.ui.common.UiState
import kotlinx.coroutines.launch

class OverviewViewModel(
    val noteRepository: NoteRepository
) : MviViewModel<OverviewViewModel.OverviewUiState>() {
    sealed interface OverviewUiState : UiState {
        object Loading : OverviewUiState
        object Error : OverviewUiState
        data class Data(val notes: Iterable<Note>) : OverviewUiState
    }

    override fun getInitialUiState() = OverviewUiState.Loading

    init {
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            noteRepository.getAll().fold(
                onFailure = { state(OverviewUiState.Error) },
                onSuccess = { state(OverviewUiState.Data(it)) }
            )
        }
    }
}