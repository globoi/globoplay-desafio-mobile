package com.app.fakegloboplay

import android.app.Application
import com.app.fakegloboplay.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FakeGloboPlayApp: Application() {
    override fun onCreate() {
        super.onCreate()

        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@FakeGloboPlayApp)
            // declare modules
            modules(AppModule.modules)
        }
    }
}