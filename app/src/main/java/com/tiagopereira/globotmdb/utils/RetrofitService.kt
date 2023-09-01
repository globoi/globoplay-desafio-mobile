package com.tiagopereira.globotmdb.utils

import com.tiagopereira.globotmdb.data.ApiResponse
import com.tiagopereira.globotmdb.data.DetailsResponse
import com.tiagopereira.globotmdb.data.VideoResponse
import com.tiagopereira.globotmdb.utils.Constants.Companion.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: String?
    ): Response<ApiResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String?,
        @Query("page") page: String?
    ): Response<ApiResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: String?
    ): Response<ApiResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String?,
        @Query("page") page: String?
    ): Response<ApiResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String?
    ): Response<DetailsResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideoId(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String?
    ): Response<VideoResponse>

    @GET("search/movie")
    suspend fun getSearchByName(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("api_key") apiKey: String,
        @Query("language") language: String?,
        @Query("page") page: String?
    ): Response<ApiResponse>

    companion object {

        private val retrofitService: RetrofitService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(RetrofitService::class.java)
        }

        fun getInstance(): RetrofitService {
            return retrofitService
        }
    }
}