package easynotecompose.data.repository

import com.dirkeisold.easynotecompose.core.model.Note
import com.dirkeisold.easynotecompose.core.repository.NoteRepository
import easynotecompose.data.database.NoteDataSource
import easynotecompose.data.repository.mapper.toNote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepositoryImpl(
    private val noteDataSource: NoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : NoteRepository {
    override suspend fun get(id: String): Result<Note?> = withContext(dispatcher) {
        runCatching {
            noteDataSource.get(id)?.toNote()
        }
    }

    override suspend fun getAll(): Result<Iterable<Note>> = withContext(dispatcher) {
        runCatching {
            noteDataSource.getAll().map { it.toNote() }
        }
    }

    override suspend fun save(note: Note) {
        withContext(dispatcher) {
            runCatching { noteDataSource.save(note.toNote()) }
        }
    }
}