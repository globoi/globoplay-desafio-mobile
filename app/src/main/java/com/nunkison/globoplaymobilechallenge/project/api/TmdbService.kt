package com.nunkison.globoplaymobilechallenge.project.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbService {
    @GET("discover/movie")
    suspend fun discoverMovie(
        @Query("with_genres") withGenres: String
    ): Response<MovieListResponse?>

    @GET("genre/movie/list")
    suspend fun genreList(): Response<GenreListResponse?>

    @GET("movie/{id}")
    suspend fun movie(
        @Path("id") id: String
    ): Response<MovieResponse?>

    @GET("movie/{id}/videos")
    suspend fun movieVideos(
        @Path("id") id: String
    ): Response<MovieVideos?>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String
    ): Response<MovieListResponse?>


}