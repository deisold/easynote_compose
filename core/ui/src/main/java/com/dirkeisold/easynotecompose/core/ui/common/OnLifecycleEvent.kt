package com.dirkeisold.easynotecompose.core.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun OnLifecycleEvent(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(key1 = lifecycleOwner.value) {
        val observer = LifecycleEventObserver { owner, event ->
            eventHandler.value(owner, event)
        }
        with(lifecycleOwner.value.lifecycle) {
            addObserver(observer)
            onDispose { removeObserver(observer) }
        }
    }
}

inline fun Lifecycle.Event.case(event: Lifecycle.Event, block: () -> Unit) {
    if (this == event) block() else Unit
}