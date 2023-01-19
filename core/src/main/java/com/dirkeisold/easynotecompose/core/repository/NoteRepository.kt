package com.dirkeisold.easynotecompose.core.repository

import com.dirkeisold.easynotecompose.core.model.Note

interface NoteRepository {
    suspend fun get(id: String): Result<Note?>
    suspend fun getAll(): Result<Iterable<Note>>
    suspend fun save(note: Note)
}