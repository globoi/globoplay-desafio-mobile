package com.nunkison.globoplaymobilechallenge

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class TvApp : Application() {
    override fun onCreate() {
        super.onCreate()

        current = this

        startKoin {
            androidContext(this@TvApp)
            modules(androidTvModule)
        }
    }

    companion object {
        private var current: TvApp? = null
        val instance: TvApp get() = current!!
    }
}