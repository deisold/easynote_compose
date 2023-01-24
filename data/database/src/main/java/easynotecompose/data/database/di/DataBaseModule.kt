package easynotecompose.data.database.di

import androidx.room.Room
import easynotecompose.data.database.NoteDataSource
import easynotecompose.data.database.db.NoteDatabase
import easynotecompose.data.database.impl.NoteDatabaseDataSource
import org.koin.dsl.module

val dataBaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = NoteDatabase::class.java,
            name = "easy-note-db"
        ).build()
    }

//    single<NoteDataSource> { NoteInMemoryDataSource() }
    single<NoteDataSource> { NoteDatabaseDataSource(noteDao = get<NoteDatabase>().getNoteDao()) }

}