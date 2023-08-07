package br.com.movies.data.repository.datasource

import br.com.movies.data.remote.dto.TrendingDto

interface RemoteDataSource {
    suspend fun getTrendingMovies(page: Int): Result<TrendingDto>

}