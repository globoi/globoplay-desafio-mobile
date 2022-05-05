package com.ftoniolo.core.data.network

import com.ftoniolo.core.data.network.response.film.FilmsDataWrapperResponse
import com.ftoniolo.core.data.network.response.genre.GenresDataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface TmdbApi {

    @GET("movie/popular")
    suspend fun getFilms(
        @QueryMap
        queries: Map<String, String>
    ): FilmsDataWrapperResponse

    @GET("genre/movie/list")
    suspend fun getGenres(
        @QueryMap
        queries: Map<String, String>
    ):GenresDataWrapperResponse
}