package br.com.movies.data.repository.datasource.trending

import br.com.common.data.dto.ResultMoviesDto

interface TrendingRemoteDataSource {
    suspend fun getTrendingMovies(page: Int): Result<ResultMoviesDto>

}