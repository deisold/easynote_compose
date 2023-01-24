package easynotecompose.data.database.impl

import easynotecompose.data.database.NoteDataSource
import easynotecompose.data.database.model.NoteEntity
import java.util.Date

class NoteInMemoryDataSource : NoteDataSource {
    private val data: MutableMap<String, NoteEntity> = mutableMapOf()

    init {
        data.putAll(
            MutableList(20) { it }.associate { index -> index.toString() to createRandomNote(index) }
        )
    }

    override suspend fun get(id: String): NoteEntity? = data[id]

    override suspend fun getAll(): Iterable<NoteEntity> = data.values

    override suspend fun save(note: NoteEntity) {
        data[note.id] = note
    }

    private fun createRandomNote(index: Int) = NoteEntity(
        id = index.toString(),
        created = Date(),
        title = "Note #${index + 1}",
        text = "Text #${index + 1}"
    )
}


