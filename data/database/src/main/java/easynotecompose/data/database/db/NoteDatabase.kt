package easynotecompose.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import easynotecompose.data.database.converter.DateConverter
import easynotecompose.data.database.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}