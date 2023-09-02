package com.gmribas.globoplaydesafiomobile.feature.home.data.source

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface GetTopRatedTvShowsSource {

    suspend fun getTopRatedTvShows(): Flow<PagingData<TvShow>>
}