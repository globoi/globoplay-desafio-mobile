package com.reisdeveloper.data.api

import com.reisdeveloper.data.dataModel.Favorite
import com.reisdeveloper.data.dataModel.MovieDetails
import com.reisdeveloper.data.dataModel.MovieList
import com.reisdeveloper.data.dataModel.MovieVideos
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("3/movie/now_playing")
    suspend fun getNowPlaying(
        @Query("language") language: String? = "pt-BR",
        @Query("page") page: Int
    ): MovieList

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String? = "pt-BR",
        @Query("page") page: Int
    ): MovieList

    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String? = "pt-BR",
        @Query("page") page: Int
    ): MovieList

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String? = "pt-BR",
        @Query("page") page: Int
    ): MovieList

    @GET("3/account/{account_id}/lists")
    suspend fun getMyLists(
        @Path("account_id") accountId: String
    ): MovieList

    @GET("3/account/{account_id}/favorite/movies")
    suspend fun getFavoriteMovies(
        @Path("account_id") accountId: String
    ): MovieList

    @GET("3/movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: String,
        @Query("language") language: String? = "pt-BR"
    ): MovieList

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query("language") language: String? = "pt-BR"
    ): MovieDetails

    @POST("3/account/{account_id}/favorite")
    suspend fun favoriteMovie(
        @Path("account_id") accountId: String,
        @Body favorite: Favorite
    ): MovieList

    @GET("3/movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: String
    ): MovieVideos

    @GET("3/search/movie")
    suspend fun searchMovies(
        @Query("language") language: String? = "pt-BR",
        @Query("query") query: String
    ): MovieList

}