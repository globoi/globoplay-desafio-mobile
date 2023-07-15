package com.nunkison.globoplaymobilechallenge.domain

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DiscoverMovieService {
    @GET("discover/movie?with_genres={genre}")
    suspend fun discoverMovie(@Path("genre") genre: String): Response<DiscoverMovieResponse?>
}