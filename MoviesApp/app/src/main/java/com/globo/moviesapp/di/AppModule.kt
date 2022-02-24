package com.globo.moviesapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun providesSharedPreferences(application: Application) : SharedPreferences {
        return application.getSharedPreferences("PREFERENCE_MOVIE", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit{
        val okHttpClient =
            OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor {
                    val originalRequest = it.request()
                    val builder = originalRequest.newBuilder()
                    val newRequest = builder.build()
                    it.proceed(newRequest)
                }.build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}