package com.gmribas.globoplaydesafiomobile.feature.home.domain.repository

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface DiscoverMoviesRepository {

    suspend fun discoverMovies(): Flow<PagingData<Movie>>
}