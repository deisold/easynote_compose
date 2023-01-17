package easynotecompose.data.repository.di

import com.dirkeisold.easynotecompose.core.repository.NoteRepository
import easynotecompose.data.repository.NoteRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<NoteRepository> { NoteRepositoryImpl(get()) }
}