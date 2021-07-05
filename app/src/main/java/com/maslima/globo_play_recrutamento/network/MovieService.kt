package com.maslima.globo_play_recrutamento.network

import com.maslima.globo_play_recrutamento.network.model.MovieDto
import com.maslima.globo_play_recrutamento.network.responses.ConfigResponse
import com.maslima.globo_play_recrutamento.network.responses.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("query") query: String,
    ): MovieResponse

    @GET("discover/movie")
    suspend fun listMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET("discover/movie")
    suspend fun listMoviesByCategory(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("with_genres") withGenres: Int
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieID: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): MovieDto

    @GET("configuration")
    suspend fun getConfigInfo(@Query("api_key") apiKey: String): ConfigResponse
}