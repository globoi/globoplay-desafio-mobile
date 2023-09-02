package com.gmribas.globoplaydesafiomobile.feature.details.data.source

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface GetSimilarTvShowsSource {

    suspend fun getSimilarSoapOperas(id: Int): Flow<PagingData<TvShow>>
}