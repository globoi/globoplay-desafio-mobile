package com.example.globechallenge.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    const val APIKEY = "67068a3613aa1fd35959028414f6a670"
    const val MOVIESERVICE = "https://api.themoviedb.org/3/"
    const val IMAGESERVICE = "https://image.tmdb.org/t/p/original"

    fun serviceMoviesDB(): Service {
        return Retrofit.Builder()
            .baseUrl(MOVIESERVICE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }.build())
            .build().create(Service::class.java)
    }
}