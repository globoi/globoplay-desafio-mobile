package br.com.nerdrapido.themoviedbapp.di

import br.com.nerdrapido.themoviedbapp.di.modules.RepositoryModule.Companion.getRepositoryModule
import br.com.nerdrapido.themoviedbapp.di.modules.RetrofitModule.Companion.getRetrofitModule
import org.koin.core.module.Module

/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 *
 * Koin injection utility
 */
class KoinManager {

    companion object {

        /**
         * Application Modules centralizer.
         */
        fun getApplicationModules(): List<Module> {
            return listOf(
                getRetrofitModule(),
                getRepositoryModule()
            )
        }
    }
}