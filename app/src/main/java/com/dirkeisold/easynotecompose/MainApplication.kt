package com.dirkeisold.easynotecompose

import android.app.Application
import easynotecompose.data.database.di.dataBaseModule
import easynotecompose.data.repository.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)

            modules(listOf(dataBaseModule, repositoryModule))
        }
    }
}