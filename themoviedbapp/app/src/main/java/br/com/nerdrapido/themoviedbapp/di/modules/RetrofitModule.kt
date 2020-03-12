package br.com.nerdrapido.themoviedbapp.di.modules

import br.com.nerdrapido.themoviedbapp.domain.retrofit.RetrofitClientInstance
import br.com.nerdrapido.themoviedbapp.domain.retrofit.ServiceInterceptor
import org.koin.dsl.module

/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
class RetrofitModule {

    companion object {
        fun getRetrofitModule() = module {
            single { ServiceInterceptor(get()) }
            single { RetrofitClientInstance(get()).retrofit }
        }
    }
}