package com.nroncari.movieplay.data.service

import com.nroncari.movieplay.BuildConfig
import com.nroncari.movieplay.data.model.BaseMovieListResponse
import com.nroncari.movieplay.data.model.MovieDetailResponse
import com.nroncari.movieplay.data.model.MovieListItemResponse
import com.nroncari.movieplay.data.retrofit.RetrofitConst.LANGUAGE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key") apiKey: String = BuildConfig.THEMOVIEDBAPI_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int,
        @Query("with_genres") genre: Int
    ): BaseMovieListResponse<MovieListItemResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetailBy(
        @Query("api_key") apiKey: String = BuildConfig.THEMOVIEDBAPI_KEY,
        @Query("language") language: String = LANGUAGE,
        @Path("movie_id") movieId: Int
    ): MovieDetailResponse
}