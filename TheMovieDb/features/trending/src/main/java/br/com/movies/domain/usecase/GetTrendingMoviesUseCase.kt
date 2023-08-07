package br.com.movies.domain.usecase

import androidx.paging.PagingData
import br.com.movies.domain.model.TrendingMovies
import br.com.movies.domain.repository.TrendingMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrendingMoviesUseCase @Inject constructor(private val repository: TrendingMoviesRepository) {
    operator fun invoke(): Flow<PagingData<TrendingMovies>> = repository.getTrendingMovies()
}