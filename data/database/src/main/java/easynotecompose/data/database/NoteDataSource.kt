package easynotecompose.data.database

import easynotecompose.data.database.data.NoteDb

interface NoteDataSource {
    suspend fun get(id: String): NoteDb?
    suspend fun getAll(): Iterable<NoteDb>
    suspend fun save(note: NoteDb)
}