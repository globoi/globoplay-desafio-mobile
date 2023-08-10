package br.com.movies.domain.repository.trending

import androidx.paging.PagingData
import br.com.common.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface TrendingMoviesRepository {
    fun getTrendingMovies() : Flow<PagingData<Movie>>
}