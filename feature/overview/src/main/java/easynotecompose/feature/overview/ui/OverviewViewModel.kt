package easynotecompose.feature.overview.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dirkeisold.easynotecompose.core.model.Note
import com.dirkeisold.easynotecompose.core.repository.NoteRepository
import com.dirkeisold.easynotecompose.core.ui.common.MviViewModel
import com.dirkeisold.easynotecompose.core.ui.common.UiAction
import com.dirkeisold.easynotecompose.core.ui.common.UiState
import kotlinx.coroutines.launch

class OverviewViewModel(
    val noteRepository: NoteRepository
) : MviViewModel<OverviewViewModel.OverviewUiState, UiAction>() {
    sealed interface OverviewUiState : UiState {
        object Loading : OverviewUiState
        object Error : OverviewUiState
        data class Data(val notes: Iterable<Note>) : OverviewUiState
    }

    override fun getInitialUiState() = OverviewUiState.Loading

    private fun loadNotes() {
        viewModelScope.launch {
            noteRepository.getAll().fold(
                onFailure = {
                    Log.w(this@OverviewViewModel::class.java.canonicalName, it)
                    state(OverviewUiState.Error)
                },
                onSuccess = { state(OverviewUiState.Data(it)) }
            )
        }
    }

    fun onResume() {
        loadNotes()
    }
}