package com.mazer.globoplayapp.data.remote

import com.mazer.globoplayapp.data.remote.responses.GenreResponse
import com.mazer.globoplayapp.data.remote.responses.MovieResponse
import com.mazer.globoplayapp.data.remote.responses.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String): Response<MovieResponse>

    @GET("genre/movie/list")
    suspend fun getGenreList(@Query("api_key") apiKey: String): Response<GenreResponse>

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendationList(@Path("movie_id") movie_id: Int, @Query("api_key") apiKey: String): Response<MovieResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideosFromMovie(@Path("movie_id") movie_id: Int, @Query("api_key") apiKey: String): Response<VideoResponse>
}