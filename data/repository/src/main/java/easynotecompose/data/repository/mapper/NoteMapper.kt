package easynotecompose.data.repository.mapper

import com.dirkeisold.easynotecompose.core.model.Note
import easynotecompose.data.database.model.NoteEntity

internal fun NoteEntity.toNote(): Note = Note(id, created, title, text)
internal fun Note.toNote(): NoteEntity = NoteEntity(id, created, title, text)