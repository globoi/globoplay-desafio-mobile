package com.gmribas.globoplaydesafiomobile.feature.details.domain.repository

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface GetSimilarTvShowsRepository {
    suspend fun getSimilarTvShows(id: Int): Flow<PagingData<TvShow>>
}