package com.example.globoplay.data.remote

import com.example.globoplay.data.remote.response.PopularMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {
    @GET("/3/movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey:String = API_KEY,
        @Query("language") language:String = LANGUAGE
    ):Call<PopularMovieResponse>

    companion object{
        const val BASE_URL = "https://api.themoviedb.org/"
        const val API_KEY = "b93f3b66947a93d495bad6ee58d8b339"
        const val LANGUAGE = "pt-BR"
    }
}