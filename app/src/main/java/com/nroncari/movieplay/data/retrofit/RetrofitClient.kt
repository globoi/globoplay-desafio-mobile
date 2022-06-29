package com.nroncari.movieplay.data.retrofit

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nroncari.movieplay.data.retrofit.RetrofitConst.BASE_URL
import com.nroncari.movieplay.data.retrofit.RetrofitConst.CACHE_SIZE
import com.nroncari.movieplay.data.retrofit.RetrofitConst.TIMEOUT
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient(
    private val application: Context
) {

    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy {
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .cache(cacheSize())
            .addNetworkInterceptor(CacheInterceptor)
            .addInterceptor(interceptor)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    fun newInstance(): Retrofit = with(Retrofit.Builder()) {
        baseUrl(BASE_URL)
        client(okHttp)
        addConverterFactory(GsonConverterFactory.create(gson))
        build()
    }

    private fun cacheSize(): Cache {
        return Cache(application.cacheDir, CACHE_SIZE)
    }
}