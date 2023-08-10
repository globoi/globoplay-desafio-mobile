package br.com.movies.data.remote

import br.com.common.data.dto.ResultMoviesDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {
    @GET("/3/trending/movie/day")
    suspend fun getTrendingMovies(@Query("page") page: Int) : Result<ResultMoviesDto>


    @GET("/3/account/{account}/favorite/movies")
    suspend fun getMoviesFavorities(@Header("Authorization") authorization: String,
                                    @Path("account") account: Int,
                                    @Query("page") page: Int) : Result<ResultMoviesDto>


}