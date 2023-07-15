package com.nunkison.globoplaymobilechallenge

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        current = this

        startKoin {
            androidContext(this@App)
            modules(androidModule)
        }
    }

    companion object{
        private var current: App? = null
        val instance: App get() = current!!
    }
}