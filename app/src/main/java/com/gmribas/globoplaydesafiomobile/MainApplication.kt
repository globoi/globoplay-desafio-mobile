package com.gmribas.globoplaydesafiomobile

import android.app.Application
import com.gmribas.globoplaydesafiomobile.core.data.database.databaseModule
import com.gmribas.globoplaydesafiomobile.core.data.di.repositoryModule
import com.gmribas.globoplaydesafiomobile.core.data.di.sourceModule
import com.gmribas.globoplaydesafiomobile.core.di.appModule
import com.gmribas.globoplaydesafiomobile.core.di.networkModule
import com.gmribas.globoplaydesafiomobile.core.domain.di.useCaseModule
import com.gmribas.globoplaydesafiomobile.core.presentation.di.uiMapperModule
import com.gmribas.globoplaydesafiomobile.core.presentation.di.viewModelModule
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
                appModule,
                networkModule,
                databaseModule,
                sourceModule,
                repositoryModule,
                useCaseModule,
                uiMapperModule,
                viewModelModule
            )

            modules(modules)
            androidContext(this@MainApplication)
            androidLogger(Level.INFO)
        }
    }
}