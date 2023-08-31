package com.gmribas.globoplaydesafiomobile.feature.home.domain.repository

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface GetTopRatedTvShowsRepository {

    suspend fun getTopRatedTvShows(): Flow<PagingData<TvShow>>
}