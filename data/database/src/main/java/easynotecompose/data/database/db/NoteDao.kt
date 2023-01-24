package easynotecompose.data.database.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import easynotecompose.data.database.model.NoteEntity

@Dao
interface NoteDao {
    @Query(
        value = """
        SELECT * FROM notes
        WHERE id = :id
    """
    )
    fun get(id: String): NoteEntity?

    @Query(value = "SELECT * FROM notes")
    fun getAll(): List<NoteEntity>

    @Upsert
    suspend fun saveOrUpdate(note: NoteEntity)
}