package com.example.globechallenge.data.network

import com.example.globechallenge.data.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("genre/movie/list")
    suspend fun getGenre(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR"
    ): GenreResponse

    @GET("movie/popular")
    suspend fun getMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR"
    ): MovieResponse

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR"
    ): MovieDetailResponse

    @GET("movie/{id}/credits")
    suspend fun getMovieCreditToGetCast(
        @Path("id") id: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR"
    ): MovieCreditResponse

    @GET("movie/{id}/videos")
    suspend fun getMovieVideos(
        @Path("id") id: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR"
    ): MovieVideosResponse
}