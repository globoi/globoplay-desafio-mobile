package com.example.globoplay

import android.app.Application
import com.example.globoplay.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App:Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(
                retrofitModule,
                apiMovieModule,
                apiSeriesModule,
                viewModelMovieModule,
                viewModelSerieModule,
                daoModule,
                repositoryModule,
                viewModelListaModule
            )

        }
    }
}