package br.com.movies.data.repository.datasource_impl

import br.com.movies.data.remote.TrendingApiService
import br.com.movies.data.remote.dto.TrendingDto
import br.com.movies.data.repository.datasource.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
        private val service: TrendingApiService) : RemoteDataSource {
    override suspend fun getTrendingMovies(page: Int): Result<TrendingDto> = service.getTrendingMovies(page)

}