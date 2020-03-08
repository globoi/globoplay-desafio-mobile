package br.com.nerdrapido.themoviedbapp.domain.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created By FELIPE GUSBERTI @ 04/03/2020
 *
 * Retrofit initialization class usin Movie Db API 4
 */
object RetrofitClientInstance {
    var retrofit: Retrofit
    private const val BASE_URL = "https://api.themoviedb.org/4/"

    init {
        val client = OkHttpClient.Builder()
            .addInterceptor(ServiceInterceptor())
            //.readTimeout(45,TimeUnit.SECONDS)
            //.writeTimeout(45,TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}