package com.example.globoplay.data.remote

import com.example.globoplay.data.remote.response.PopularMovieResponse
import com.example.globoplay.data.remote.response.PopularTVSeriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SeriesAPI {
    @GET("/3/tv/popular")
    fun getPopularSeries(
        @Query("api_key") apiKey:String = API_KEY,
        @Query("language") language:String = MoviesAPI.LANGUAGE
    ): Call<PopularTVSeriesResponse>

    companion object{
        val BASE_URL = "http://api.themoviedb.org/"
        val API_KEY = "b93f3b66947a93d495bad6ee58d8b339"
        val LANGUAGE = "pt-BR"
    }
}