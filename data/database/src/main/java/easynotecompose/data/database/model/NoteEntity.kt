package easynotecompose.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey
    val id: String,
    val created: Date,
    val title: String,
    val text: String?
)