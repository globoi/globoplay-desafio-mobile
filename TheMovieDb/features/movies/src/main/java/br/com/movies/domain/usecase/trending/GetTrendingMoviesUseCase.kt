package br.com.movies.domain.usecase.trending

import androidx.paging.PagingData
import br.com.common.domain.model.Movie
import br.com.movies.domain.repository.trending.TrendingMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrendingMoviesUseCase @Inject constructor(
    private val repository: TrendingMoviesRepository) {
    operator fun invoke(): Flow<PagingData<Movie>>
    = repository.getTrendingMovies()
}