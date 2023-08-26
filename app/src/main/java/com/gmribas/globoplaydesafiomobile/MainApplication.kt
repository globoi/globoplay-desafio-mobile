package com.gmribas.globoplaydesafiomobile

import android.app.Application
import com.gmribas.globoplaydesafiomobile.core.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            val modules = listOf(
                networkModule,
//                sourceModule,
//                repositoryMapperModule,
//                repositoryModule,
//                useCaseModule,
//                viewModelModule,
//                mapperModule
            )

            modules(modules)
            androidContext(this@MainApplication)
            androidLogger(Level.INFO)
        }
    }
}