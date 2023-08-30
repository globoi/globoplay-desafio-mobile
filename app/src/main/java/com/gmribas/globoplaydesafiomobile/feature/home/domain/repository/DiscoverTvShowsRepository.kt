package com.gmribas.globoplaydesafiomobile.feature.home.domain.repository

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface DiscoverTvShowsRepository {

    suspend fun discoverTvShows(): Flow<PagingData<TvShow>>
}