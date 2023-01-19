package easynotecompose.data.repository

import com.dirkeisold.easynotecompose.core.model.Note
import com.dirkeisold.easynotecompose.core.repository.NoteRepository
import easynotecompose.data.database.NoteDataSource
import easynotecompose.data.repository.mapper.toNote

class NoteRepositoryImpl(val noteDataSource: NoteDataSource) : NoteRepository {
    override suspend fun get(id: String): Result<Note?> = runCatching {
        noteDataSource.get(id)?.toNote()
    }

    override suspend fun getAll(): Result<Iterable<Note>> = runCatching {
        noteDataSource.getAll().map { it.toNote() }
    }

    override suspend fun save(note: Note) = noteDataSource.save(note.toNote())
}