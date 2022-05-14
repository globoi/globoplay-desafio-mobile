package com.simonassi.globoplay.api

import com.simonassi.globoplay.BuildConfig
import com.simonassi.globoplay.data.MovieSearchResponse
import com.simonassi.globoplay.data.TvSearchResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "pt-BR",
        @Query("api_key") clientId: String = BuildConfig.TMDB_ACCESS_KEY
    ): MovieSearchResponse

    @GET("discover/tv")
    suspend fun discoverTvs(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "pt-BR",
        @Query("api_key") clientId: String = BuildConfig.TMDB_ACCESS_KEY
    ): TvSearchResponse

    companion object {
        private const val BASE_URL = BuildConfig.BASE_URL

        fun create(): TMDBService {

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TMDBService::class.java)
        }
    }
}