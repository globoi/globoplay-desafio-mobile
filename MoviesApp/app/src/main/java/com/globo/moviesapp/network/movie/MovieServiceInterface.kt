package com.globo.moviesapp.network.movie

import com.globo.moviesapp.model.movieUpdateFavorite.MovieUpdateFavorite
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface MovieServiceInterface {
    @GET("/3/tv/{movie_id}/videos")
    fun getMovieVideosCall(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): Call<JsonObject>

    @POST("/3/account/{account_id}/favorite")
    fun updateFavoriteMovie(
        @Path("account_id") accountId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("session_id") sessionId: String,
        @Body dataJson: MovieUpdateFavorite,
    ): Call<JsonObject>

    @GET("/3/tv/{movie_id}/account_states")
    fun checkMovieFavoriteCall(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("session_id") sessionId: String
    ): Call<JsonObject>

    @GET("/3/account/{account_id}/favorite/tv")
    fun getMovieFavoriteCall(
        @Path("account_id") accountId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("session_id") sessionId: String
    ): Call<JsonObject>

    @GET("/3/tv/{movieId}/recommendations")
    fun getMovieRecommendationsCall(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<JsonObject>

    @GET("/3/tv/{movieId}/aggregate_credits")
    fun openMovieAggregateCreditsCall(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<JsonObject>

    @GET("/3/tv/{movieId}")
    fun openMovieCall(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<JsonObject>

    @GET("/3/discover/tv")
    fun getMovieCall(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("with_genres") genre: String
    ): Call<JsonObject>
}