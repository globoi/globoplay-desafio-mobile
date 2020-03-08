package br.com.nerdrapido.themoviedbapp.di.modules

import br.com.nerdrapido.themoviedbapp.data.repository.login.LoginRepository
import br.com.nerdrapido.themoviedbapp.data.repository.login.LoginRepositoryImpl
import org.koin.dsl.module

/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
class RepositoryModule {

    companion object {

        fun getRepositoryModule() = module {

            single<LoginRepository> { LoginRepositoryImpl(get()) }

        }
    }
}