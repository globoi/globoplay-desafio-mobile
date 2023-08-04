package com.reisdeveloper.data.api

import com.reisdeveloper.data.dataModel.Favorite
import com.reisdeveloper.data.dataModel.MovieDetails
import com.reisdeveloper.data.dataModel.MovieList
import com.reisdeveloper.data.dataModel.MovieVideos
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MovieApi {

    //Todo alterar response
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
        @Path("movie_id") movieId: String
    ): MovieList

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String
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

}