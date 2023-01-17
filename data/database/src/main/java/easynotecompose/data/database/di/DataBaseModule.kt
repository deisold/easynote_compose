package easynotecompose.data.database.di

import easynotecompose.data.database.NoteDataSource
import easynotecompose.data.database.impl.InMemoryNoteDataSource
import org.koin.dsl.module

val dataBaseModule = module {
    single<NoteDataSource> { InMemoryNoteDataSource() }
}