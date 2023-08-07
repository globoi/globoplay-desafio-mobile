package br.com.movies.data.remote

import br.com.movies.data.remote.dto.TrendingDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TrendingApiService {
    @GET("/3/trending/movie/day")
    suspend fun getTrendingMovies(@Query("page") page: Int) : Result<TrendingDto>

}