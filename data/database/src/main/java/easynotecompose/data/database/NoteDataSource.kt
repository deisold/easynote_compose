package easynotecompose.data.database

import easynotecompose.data.database.model.NoteEntity

interface NoteDataSource {
    suspend fun get(id: String): NoteEntity?
    suspend fun getAll(): Iterable<NoteEntity>
    suspend fun create(note: NoteEntity): NoteEntity
    suspend fun save(note: NoteEntity)
}