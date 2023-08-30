package com.gmribas.globoplaydesafiomobile.core.data.network

import com.gmribas.globoplaydesafiomobile.BuildConfig
import com.gmribas.globoplaydesafiomobile.core.constants.Constants.BRAZIL_ORIGIN_COUNTRY
import com.gmribas.globoplaydesafiomobile.core.constants.Constants.SOAP_OPERA_GENRE
import com.gmribas.globoplaydesafiomobile.core.data.dto.MovieDTO
import com.gmribas.globoplaydesafiomobile.core.data.dto.MovieDetailsDTO
import com.gmribas.globoplaydesafiomobile.core.data.dto.PageDTO
import com.gmribas.globoplaydesafiomobile.core.data.dto.SoapOperaDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int,
        @Query("language") language: String = "pt-br"

    ): PageDTO<MovieDTO>

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "pt-br"

    ): MovieDetailsDTO

    @GET("movie/{movieId}/similar")
    suspend fun getSimilarMovies(
        @Path("movieId") movieId: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "pt-br"

    ): PageDTO<MovieDTO>

    @GET("discover/tv")
    suspend fun discoverSoapOperas(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int,
        @Query("language") language: String = "pt-br",
        @Query("with_genres") withGenres: Int = SOAP_OPERA_GENRE,
        @Query("with_origin_country") withOriginCountry: String = BRAZIL_ORIGIN_COUNTRY
    ): PageDTO<SoapOperaDTO>

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int,
        @Query("language") language: String = "pt-br"
    ): PageDTO<SoapOperaDTO>

    @GET("movie/{id}/similar")
    suspend fun getSimilarTvShows(
        @Path("id") id: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "pt-br"

    ): PageDTO<SoapOperaDTO>
}