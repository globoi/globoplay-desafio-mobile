package com.gmribas.globoplaydesafiomobile.feature.details.domain.repository

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetSimilarMoviesRepository {
    suspend fun getSimilarMovies(movieId: Int): Flow<PagingData<Movie>>
}