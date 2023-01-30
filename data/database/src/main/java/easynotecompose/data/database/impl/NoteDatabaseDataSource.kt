package easynotecompose.data.database.impl

import easynotecompose.data.database.NoteDataSource
import easynotecompose.data.database.db.NoteDao
import easynotecompose.data.database.model.NoteEntity

class NoteDatabaseDataSource(private val noteDao: NoteDao) : NoteDataSource {
    private val data: MutableMap<String, NoteEntity> = mutableMapOf()

    override suspend fun get(id: String): NoteEntity? = noteDao.get(id)

    override suspend fun getAll(): Iterable<NoteEntity> = noteDao.getAll()

    override suspend fun create(note: NoteEntity): NoteEntity {
        noteDao.saveOrUpdate(note)
        return note
    }

    override suspend fun save(note: NoteEntity) {
        noteDao.saveOrUpdate(note)
    }
}


