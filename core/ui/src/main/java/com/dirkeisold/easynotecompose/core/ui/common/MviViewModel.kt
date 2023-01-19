package com.dirkeisold.easynotecompose.core.ui.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class MviViewModel<STATE : UiState> : ViewModel() {
    private val _uiState: MutableStateFlow<STATE> by lazy { MutableStateFlow(getInitialUiState()) }
    val uiState: StateFlow<STATE> by lazy { _uiState }

    abstract fun getInitialUiState(): STATE

    protected fun state(newState:STATE){
        _uiState.tryEmit(newState)
    }
}