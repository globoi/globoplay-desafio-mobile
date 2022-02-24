package com.globo.moviesapp.network.genre

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreServiceInterface {
    @GET("/3/genre/tv/list")
    fun getGenreCall(@Query("api_key") apiKey: String,
                     @Query("language") language: String): Call<JsonObject>
}