package com.example.globoplay.viewmodel

import com.example.globoplay.data.remote.MoviesAPI
import com.example.globoplay.data.remote.SeriesAPI
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object HTTPClient {
    private val retrofit  = Retrofit.Builder()
            .client(
                OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
                        .setLevel(HttpLoggingInterceptor.Level.HEADERS)
                ).build()
            )
            .baseUrl(MoviesAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    fun getMoviesAPI() = retrofit.create(MoviesAPI::class.java)
   fun getSeriesAPI() = retrofit.create(SeriesAPI::class.java)
}