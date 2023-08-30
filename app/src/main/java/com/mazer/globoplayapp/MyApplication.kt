package com.mazer.globoplayapp

import android.app.Application
import android.util.Log
import com.mazer.globoplayapp.presentation.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("KoinDebug", "App started")
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule)
            Log.d("KoinDebug", "passou modules")
        }
    }
}