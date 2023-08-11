package br.com.details_movie.data.remote

import br.com.details_movie.data.remote.dto.MovieDetailsDto
import br.com.details_movie.data.remote.dto.MovieDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {
    @GET("/3/movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieId: Int): Result<MovieDetailsDto>
}