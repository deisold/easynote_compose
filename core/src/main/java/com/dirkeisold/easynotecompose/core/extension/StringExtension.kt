package com.dirkeisold.easynotecompose.core.extension

inline fun <reified T> String?.takeIfNotEmpty(func: (value:String) -> T) =
    this?.takeIf { it.isNotBlank() }?.let { func(it) }