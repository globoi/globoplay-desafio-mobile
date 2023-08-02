package com.reisdeveloper.globoplay

import android.app.Application
import com.reisdeveloper.data.di.dataModule
import com.reisdeveloper.domain.di.domainModule
import com.reisdeveloper.globoplay.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class GloboplayApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@GloboplayApplication)
            modules(
                listOf(
                    appModule
                )
            )
        }
        GlobalContext.loadKoinModules(
            listOf(
                domainModule,
                dataModule
            )
        )

    }
}