package com.dirkeisold.easynotecompose.core.extension

inline fun <reified T> String?.takeIfNotEmpty(func: () -> T) =
    this?.takeIf { it.isNotBlank() }?.let { func() }