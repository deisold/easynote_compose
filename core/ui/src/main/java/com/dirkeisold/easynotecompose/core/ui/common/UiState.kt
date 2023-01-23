package com.dirkeisold.easynotecompose.core.ui.common

interface UiState

inline fun <reified T> UiState.case(block: T.() -> Unit) {
    (this as? T)?.let(block)
}