package com.gmribas.globoplaydesafiomobile.feature.home.data.repository

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.feature.home.data.source.DiscoverMoviesSource
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.feature.home.domain.repository.DiscoverMoviesRepository
import kotlinx.coroutines.flow.Flow

class DiscoverMoviesRepositoryImpl(
    private val source: DiscoverMoviesSource
): DiscoverMoviesRepository {

    override suspend fun discoverMovies(): Flow<PagingData<Movie>> = source.discoverMovies()
}