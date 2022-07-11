package com.nroncari.movieplay.data.remotedatasource.service

import com.nroncari.movieplay.BuildConfig
import com.nroncari.movieplay.data.model.BaseMovieListResponse
import com.nroncari.movieplay.data.model.BaseMovieVideoListResponse
import com.nroncari.movieplay.data.model.MovieDetailResponse
import com.nroncari.movieplay.data.remotedatasource.retrofit.RetrofitConst.LANGUAGE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRemoteService {

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key") apiKey: String = BuildConfig.THEMOVIEDBAPI_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int,
        @Query("with_genres") genre: Int
    ): BaseMovieListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetailBy(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = BuildConfig.THEMOVIEDBAPI_KEY,
        @Query("language") language: String = LANGUAGE,
    ): MovieDetailResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieRecommendationsBy(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = BuildConfig.THEMOVIEDBAPI_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): BaseMovieListResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieDataVideo(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = BuildConfig.THEMOVIEDBAPI_KEY,
        @Query("language") language: String = LANGUAGE,
    ): BaseMovieVideoListResponse

    @GET("search/movie")
    suspend fun getMoviesByKeyword(
        @Query("query") keyword: String,
        @Query("api_key") clientId: String = BuildConfig.THEMOVIEDBAPI_KEY,
        @Query("language") language: String = LANGUAGE,
    ): BaseMovieListResponse
}