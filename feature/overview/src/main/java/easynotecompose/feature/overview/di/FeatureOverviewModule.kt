package easynotecompose.feature.overview.di

import easynotecompose.feature.overview.ui.OverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureOverviewModule = module {
    viewModel { OverviewViewModel(noteRepository = get()) }
}