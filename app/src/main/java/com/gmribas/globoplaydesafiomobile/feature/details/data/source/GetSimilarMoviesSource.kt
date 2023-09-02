package com.gmribas.globoplaydesafiomobile.feature.details.data.source

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetSimilarMoviesSource {

    suspend fun getSimilarMovies(movieId: Int): Flow<PagingData<Movie>>
}