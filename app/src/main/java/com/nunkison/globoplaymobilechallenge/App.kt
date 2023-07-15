package com.nunkison.globoplaymobilechallenge

import android.app.Application
import android.content.Context
import android.os.Build
import org.koin.core.context.GlobalContext.startKoin
import java.io.File
import java.io.FileInputStream
import java.util.Properties

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(androidModule)
        }
    }
}