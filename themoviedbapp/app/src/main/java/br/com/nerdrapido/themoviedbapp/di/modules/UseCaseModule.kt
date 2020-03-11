package br.com.nerdrapido.themoviedbapp.di.modules

import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.AccessTokenUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.RequestLoginUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.SetAccessTokenUseCase
import org.koin.dsl.module

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class UseCaseModule {
    companion object {
        fun getUseCaseModule() = module {
            single { RequestLoginUseCase(get()) }
            single { GetLogInStateUseCase(get()) }
            single { AccessTokenUseCase(get()) }
            single { SetAccessTokenUseCase(get()) }
        }
    }
}