package com.com.globo.repository

import com.com.globo.BuildConfig
import com.com.globo.details.model.MovieDetailsResponse
import com.com.globo.repository.model.MoviesResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{category}")
    fun getMovies(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE
    ): Observable<Response<MoviesResponse>>

    @GET("movie/{movie_id}")
    fun getMoviesDetails(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE
    ): Observable<Response<MovieDetailsResponse>>
}
