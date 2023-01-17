package com.dirkeisold.easynotecompose.core.repository

import com.dirkeisold.easynotecompose.core.model.Note

interface NoteRepository {
    suspend fun get(id: String): Note?
    suspend fun getAll(): Iterable<Note>
    suspend fun save(note: Note)
}