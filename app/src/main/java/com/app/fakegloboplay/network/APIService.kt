package com.app.fakegloboplay.network

import com.app.fakegloboplay.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {

    private fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL_BASE)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .build()
    }

    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .build()
    }

    fun provideService(retrofit: Retrofit): APIPlay = retrofit.create(APIPlay::class.java)
}