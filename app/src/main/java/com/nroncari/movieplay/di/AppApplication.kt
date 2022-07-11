package com.nroncari.movieplay.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(databaseModule)
            modules(daoModule)
            modules(dataModules)
            modules(domainModules)
            modules(networkModules)
            modules(presentationModules)
        }
    }
}