package com.dirkeisold.easynotecompose

import com.dirkeisold.easynotecompose.core.model.ThemeConfig
import com.dirkeisold.easynotecompose.core.model.UserData
import com.dirkeisold.easynotecompose.core.ui.common.MviViewModel
import com.dirkeisold.easynotecompose.core.ui.common.UiAction
import com.dirkeisold.easynotecompose.core.ui.common.UiEvent
import com.dirkeisold.easynotecompose.core.ui.common.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : MviViewModel<MainViewModel.MainUiState, UiAction, UiEvent>() {
    sealed interface MainUiState : UiState {
        object Loading : MainUiState
        data class Data(val userData: UserData) : MainUiState
    }

    override fun getInitialUiState() = MainUiState.Loading

    init {
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000L)
            state(MainUiState.Data(UserData(themeConfig = ThemeConfig.Light)))
        }
    }
}