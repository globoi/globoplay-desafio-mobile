package br.com.nerdrapido.themoviedbapp.domain.retrofit

import br.com.nerdrapido.themoviedbapp.BuildConfig
import br.com.nerdrapido.themoviedbapp.constant.URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created By FELIPE GUSBERTI @ 04/03/2020
 *
 * Retrofit initialization class using Movie Db API 4
 */
class RetrofitInitializer(serviceInterceptor: Interceptor) {
    var retrofit: Retrofit

    init {
        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor(serviceInterceptor)
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(logging)
        }
        retrofit = Retrofit.Builder()
            .baseUrl(URL.BASE_TMDB_API.url)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}