package com.ftoniolo.core.data.network

import com.ftoniolo.core.data.network.response.film.FilmsDataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface TmdbApi {

    @GET("movie/popular")
    suspend fun getFilms(
        @QueryMap
        queries: Map<String, String>
    ): FilmsDataWrapperResponse
}