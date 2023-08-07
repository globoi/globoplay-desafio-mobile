package br.com.movies.domain.repository

import androidx.paging.PagingData
import br.com.movies.domain.model.TrendingMovies
import kotlinx.coroutines.flow.Flow

interface TrendingMoviesRepository {
    fun getTrendingMovies() : Flow<PagingData<TrendingMovies>>
}