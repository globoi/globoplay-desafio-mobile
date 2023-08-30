package com.gmribas.globoplaydesafiomobile.feature.details.data.repository

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.feature.details.data.source.GetSimilarMoviesSource
import com.gmribas.globoplaydesafiomobile.feature.details.domain.repository.GetSimilarMoviesRepository
import kotlinx.coroutines.flow.Flow

class GetSimilarMoviesRepositoryImpl(private val source: GetSimilarMoviesSource): GetSimilarMoviesRepository {
    override suspend fun getSimilarMovies(movieId: Int): Flow<PagingData<Movie>> {
        return source.getSimilarMovies(movieId)
    }
}