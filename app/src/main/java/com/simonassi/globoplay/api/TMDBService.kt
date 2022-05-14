package com.simonassi.globoplay.api

import com.simonassi.globoplay.BuildConfig
import com.simonassi.globoplay.data.TMDBSearchResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("api_key") clientId: String = BuildConfig.TMDB_ACCESS_KEY
    ): TMDBSearchResponse

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        fun create(): TMDBService {
//            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

//            val client = OkHttpClient.Builder()
////                .addInterceptor(logger)
//                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TMDBService::class.java)
        }
    }
}