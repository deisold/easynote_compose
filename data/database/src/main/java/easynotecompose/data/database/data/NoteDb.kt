package easynotecompose.data.database.data

import java.util.Date

data class NoteDb (
    val id: String,
    val created: Date,
    val title: String,
    val text: String?
)