package com.nunkison.globoplaymobilechallenge

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MobileApp : Application() {
    override fun onCreate() {
        super.onCreate()

        current = this

        startKoin {
            androidContext(this@MobileApp)
            modules(androidMobileModule)
        }
    }

    companion object {
        private var current: MobileApp? = null
        val instance: MobileApp get() = current!!
    }
}