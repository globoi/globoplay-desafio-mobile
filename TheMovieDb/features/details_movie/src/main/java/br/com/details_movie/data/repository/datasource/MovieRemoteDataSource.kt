package br.com.details_movie.data.repository.datasource

import br.com.details_movie.data.remote.dto.MovieDetailsDto
import br.com.details_movie.data.remote.dto.MovieDto

interface MovieRemoteDataSource {
    suspend fun getMovie(movieId: Int): Result<MovieDetailsDto>

}