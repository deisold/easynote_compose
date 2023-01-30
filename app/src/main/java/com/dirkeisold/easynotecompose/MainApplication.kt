package com.dirkeisold.easynotecompose

import android.app.Application
import com.dirkeisold.easynotecompose.di.mainModule
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

            modules(getCoreKoinModules())
            modules(getFeatureKoinModules())
        }
    }

    private fun getCoreKoinModules() = listOf(dataBaseModule, repositoryModule)
    private fun getFeatureKoinModules() = listOf(
        mainModule,
        featureOverviewModule, featureDetailsModule
    )
}