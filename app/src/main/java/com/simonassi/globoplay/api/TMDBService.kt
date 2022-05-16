package com.simonassi.globoplay.api

import com.simonassi.globoplay.BuildConfig
import com.simonassi.globoplay.data.movie.Movie
import com.simonassi.globoplay.data.movie.MovieSearchResponse
import com.simonassi.globoplay.data.tv.Tv
import com.simonassi.globoplay.data.tv.TvSearchResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/{movie_id}")
    suspend fun findMovieById(
        @Path("movie_id") id: Long,
        @Query("language") language: String = "pt-BR",
        @Query("api_key") clientId: String = BuildConfig.TMDB_ACCESS_KEY
    ): Movie

    @GET("tv/{tv_id}")
    suspend fun findTvById(
        @Path("tv_id") id: Long,
        @Query("language") language: String = "pt-BR",
        @Query("api_key") clientId: String = BuildConfig.TMDB_ACCESS_KEY
    ): Tv

    @GET("trending/movie/day")
    suspend fun discoverTrendingMovies(
        @Query("language") language: String = "pt-BR",
        @Query("api_key") clientId: String = BuildConfig.TMDB_ACCESS_KEY
    ): MovieSearchResponse


    @GET("discover/movie")
    suspend fun getRelatedMovies(
        @Query("with_genres") genreId: Long,
        @Query("language") language: String = "pt-BR",
        @Query("api_key") clientId: String = BuildConfig.TMDB_ACCESS_KEY
    ): MovieSearchResponse

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