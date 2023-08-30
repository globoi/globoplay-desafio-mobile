package com.gmribas.globoplaydesafiomobile.feature.details.data.source

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.SoapOpera
import kotlinx.coroutines.flow.Flow

interface GetSimilarTvShowsSource {

    suspend fun getSimilarSoapOperas(id: Int): Flow<PagingData<SoapOpera>>
}