package easynotecompose.data.repository.mapper

import com.dirkeisold.easynotecompose.core.model.Note
import easynotecompose.data.database.data.NoteDb

internal fun NoteDb.toNote(): Note = Note(id, created, title, text)
internal fun Note.toNote(): NoteDb = NoteDb(id, created, title, text)