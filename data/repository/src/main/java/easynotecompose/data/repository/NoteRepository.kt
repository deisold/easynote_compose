package easynotecompose.data.repository

import com.dirkeisold.easynotecompose.core.model.Note
import com.dirkeisold.easynotecompose.core.repository.NoteRepository
import easynotecompose.data.database.NoteDataSource
import easynotecompose.data.repository.mapper.toNoteEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date
import java.util.UUID

class NoteRepositoryImpl(
    private val noteDataSource: NoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : NoteRepository {
    override suspend fun get(id: String): Result<Note?> = withContext(dispatcher) {
        runCatching {
            noteDataSource.get(id)?.toNoteEntity()
        }
    }

    override suspend fun getAll(): Result<Iterable<Note>> = withContext(dispatcher) {
        runCatching {
            noteDataSource.getAll().map { it.toNoteEntity() }
        }
    }

    override suspend fun create(title: String, text: String?): Result<Note> =
        withContext(dispatcher) {
            val newNote = Note(
                id = UUID.randomUUID().toString(),
                created = Date(),
                title = title,
                text = text
            )
            runCatching {
                noteDataSource.create(newNote.toNoteEntity()).toNoteEntity()
            }
        }

    override suspend fun save(note: Note) {
        withContext(dispatcher) {
            runCatching { noteDataSource.save(note.toNoteEntity()) }
        }
    }
}