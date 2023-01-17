package com.dirkeisold.easynotecompose.core.model

import java.util.Date

data class Note(
    val id: String,
    val created: Date,
    val title: String,
    val text: String?
)
