package com.dirkeisold.easynotecompose.core.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable.invokeOnCompletion
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class MviViewModel<STATE : UiState, ACTION : UiAction> : ViewModel() {
    private val _uiState: MutableStateFlow<STATE> by lazy { MutableStateFlow(getInitialUiState()) }
    val uiState: StateFlow<STATE> by lazy { _uiState }

    private val _uiAction: MutableSharedFlow<ACTION> by lazy { MutableSharedFlow() }
    private var collectingUiActionJob: Job? = null

    abstract fun getInitialUiState(): STATE

    protected fun state(newState: STATE) {
        _uiState.tryEmit(newState)
    }

    protected fun action(action: ACTION) {
        if (collectingUiActionJob == null) {
            collectUiActions()
        }
        _uiAction.tryEmit(action)
    }

    @Suppress("DEPRECATION")
    private fun collectUiActions() {
        collectingUiActionJob?.cancel()
        collectingUiActionJob = viewModelScope.launch {
            _uiAction.collect(::onUiAction)
        }.also {
            invokeOnCompletion {
                collectingUiActionJob = null
            }
        }
    }

    open fun onUiAction(action: ACTION) = Unit
}