package easynotecompose.data.repository.mapper

import com.dirkeisold.easynotecompose.core.model.Note
import easynotecompose.data.database.model.NoteEntity

internal fun NoteEntity.toNoteEntity(): Note = Note(id, created, title, text)
internal fun Note.toNoteEntity(): NoteEntity = NoteEntity(id, created, title, text)