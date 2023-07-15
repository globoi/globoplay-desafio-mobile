package com.nunkison.globoplaymobilechallenge.project.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {
    @GET("discover/movie")
    suspend fun discoverMovie(
        @Query("with_genres") withGenres: String
    ): Response<DiscoverMovieResponse?>

    @GET("genre/movie/list")
    suspend fun genreList(): Response<GenreListResponse?>
}