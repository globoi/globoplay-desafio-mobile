package com.ftoniolo.globoplay.framework.network

import com.ftoniolo.globoplay.framework.network.response.film.FilmResponse
import com.ftoniolo.globoplay.framework.network.response.film.FilmsDataWrapperResponse
import com.ftoniolo.globoplay.framework.network.response.watchtoo.WatchTooResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface TmdbApi {

    @GET("movie/popular")
    suspend fun getPopularFilms(
    ): FilmsDataWrapperResponse<FilmResponse>

    @GET("discover/movie")
    suspend fun getMoviesByCategory(
        @Query("with_genres")
        genreId: Long
    ): FilmsDataWrapperResponse<FilmResponse>

    @GET("movie/{filmId}/recommendations")
    suspend fun getWatchToo(
        @Path("filmId")
        filmId: Long,
        @QueryMap
        queries: Map<String, String>
    ):FilmsDataWrapperResponse<WatchTooResponse>
}