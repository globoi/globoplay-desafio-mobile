package br.com.nerdrapido.themoviedbapp.ui

import android.app.Application
import br.com.nerdrapido.themoviedbapp.di.KoinManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree


/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class StartApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Koin. It ust be initialized here in a star application
        startKoin {
            // declare used Android context
            androidContext(this@StartApplication)
            // declare modules
            modules(KoinManager.getApplicationModules())
        }

        // Initializes timber logger
        Timber.plant(DebugTree())
    }


}