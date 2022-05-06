package com.ftoniolo.globoplay.framework.network

import com.ftoniolo.globoplay.framework.network.response.film.FilmsDataWrapperResponse
import com.ftoniolo.globoplay.framework.network.response.genre.GenresDataWrapperResponse
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