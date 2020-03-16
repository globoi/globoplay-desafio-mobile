package br.com.nerdrapido.themoviedbapp.di.modules

import br.com.nerdrapido.themoviedbapp.domain.usecase.*
import org.koin.dsl.module

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class UseCaseModule {
    companion object {
        fun getUseCaseModule() = module {
            single { SetSessionUseCase(get(), get()) }
            single { CreateSessionuseCase(get()) }
            single { GetLanguageUseCase() }
            single { RequestLoginUseCase(get()) }
            single { GetLogInStateUseCase(get()) }
            single { LogoutUseCase(get(), get()) }
            single { GetDiscoverUseCase(get(), get(), get()) }
            single { MovieUseCase(get(), get(), get()) }
            single { FavoriteMoviesUseCase(get(), get(), get()) }
            single { WatchlistMoviesUseCase(get(), get(), get()) }
        }
    }
}