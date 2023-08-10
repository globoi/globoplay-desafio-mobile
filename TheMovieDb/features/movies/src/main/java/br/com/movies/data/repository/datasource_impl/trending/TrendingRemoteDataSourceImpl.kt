package br.com.movies.data.repository.datasource_impl.trending

import br.com.movies.data.remote.MoviesApiService
import br.com.common.data.dto.ResultMoviesDto
import br.com.movies.data.repository.datasource.trending.TrendingRemoteDataSource
import javax.inject.Inject

class TrendingRemoteDataSourceImpl @Inject constructor(
        private val service: MoviesApiService) :
    TrendingRemoteDataSource {
    override suspend fun getTrendingMovies(page: Int): Result<ResultMoviesDto> = service.getTrendingMovies(page)

}