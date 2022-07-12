package com.nroncari.movieplay.utils

import android.app.Application
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.nroncari.movieplay.data.localdatasource.ConnectionDatabase
import com.nroncari.movieplay.di.*
import okhttp3.Connection
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://127.0.0.1:8080"

class TestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(dataModules)
            modules(domainModules)
            modules(networkModules)
            modules(presentationModules)
            modules(networkAndroidTestModule)
        }
    }

    private val networkAndroidTestModule = module(override = true) {
        single { OkHttpClient.Builder().build() }

        single {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}