package com.dirkeisold.easynotecompose

import android.app.Application
import easynotecompose.data.database.di.dataBaseModule
import easynotecompose.data.repository.di.repositoryModule
import easynotecompose.feature.details.di.featureDetailsModule
import easynotecompose.feature.overview.di.featureOverviewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)

            modules(getCoreKoinModule())
            modules(getFeatureKoinModule())
        }
    }

    private fun getCoreKoinModule() = listOf(dataBaseModule, repositoryModule)
    private fun getFeatureKoinModule() = listOf(featureOverviewModule, featureDetailsModule)
}