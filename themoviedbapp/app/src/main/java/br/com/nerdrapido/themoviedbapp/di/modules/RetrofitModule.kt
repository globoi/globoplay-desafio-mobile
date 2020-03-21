package br.com.nerdrapido.themoviedbapp.di.modules

import br.com.nerdrapido.themoviedbapp.domain.retrofit.MockErrorServiceInterceptor
import br.com.nerdrapido.themoviedbapp.domain.retrofit.RetrofitInitializer
import br.com.nerdrapido.themoviedbapp.domain.retrofit.ServiceInterceptor
import okhttp3.Interceptor
import org.koin.dsl.module

/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
class RetrofitModule {

    companion object {
        fun getRetrofitModule() = module {
//            single<Interceptor>(override = true) { ServiceInterceptor(get()) }
            single<Interceptor>(override = true) { MockErrorServiceInterceptor() }
            single { RetrofitInitializer(get()).retrofit }
        }
    }
}