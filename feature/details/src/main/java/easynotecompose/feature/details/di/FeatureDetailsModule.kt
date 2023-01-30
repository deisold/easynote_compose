package easynotecompose.feature.details.di

import androidx.lifecycle.SavedStateHandle
import easynotecompose.feature.details.ui.create.CreateNoteViewModel
import easynotecompose.feature.details.ui.details.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureDetailsModule = module {
    viewModel { (handle: SavedStateHandle) ->
        DetailsViewModel(
            noteRepository = get(),
            savedStateHandle = handle
        )
    }

    viewModel { CreateNoteViewModel(noteRepository = get()) }
}