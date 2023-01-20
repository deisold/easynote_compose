package easynotecompose.data.database.impl

import easynotecompose.data.database.NoteDataSource
import easynotecompose.data.database.data.NoteDb
import java.util.Date

class InMemoryNoteDataSource : NoteDataSource {
    private val data: MutableMap<String, NoteDb> = mutableMapOf()

    init {
        data.putAll(
            MutableList(20) { it }.associate { index -> index.toString() to createRandomNote(index) }
        )
    }

    override suspend fun get(id: String): NoteDb? = data[id]

    override suspend fun getAll(): Iterable<NoteDb> = data.values

    override suspend fun save(note: NoteDb) {
        data[note.id] = note
    }

    private fun createRandomNote(index: Int) = NoteDb(
        id = index.toString(),
        created = Date(),
        title = "Note #${index + 1}",
        text = "Text #${index + 1}"
    )
}


