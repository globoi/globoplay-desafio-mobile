package com.gmribas.globoplaydesafiomobile.feature.home.data.repository

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.feature.home.data.source.DiscoverTvShowSource
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShow
import com.gmribas.globoplaydesafiomobile.feature.home.domain.repository.DiscoverTvShowsRepository
import kotlinx.coroutines.flow.Flow

class DiscoverTvShowsRepositoryImpl(
    private val source: DiscoverTvShowSource
): DiscoverTvShowsRepository {

    override suspend fun discoverTvShows(): Flow<PagingData<TvShow>> = source.discoverTvShows()
}