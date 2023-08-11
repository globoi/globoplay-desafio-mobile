package br.com.details_movie.data.repository.datasourceimpl

import br.com.details_movie.data.remote.MovieApiService
import br.com.details_movie.data.remote.dto.MovieDetailsDto
import br.com.details_movie.data.remote.dto.MovieDto
import br.com.details_movie.data.repository.datasource.MovieRemoteDataSource
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val service:MovieApiService
) : MovieRemoteDataSource {
    override suspend fun getMovie(movieId: Int): Result<MovieDetailsDto> = service.getMovie(movieId)

}