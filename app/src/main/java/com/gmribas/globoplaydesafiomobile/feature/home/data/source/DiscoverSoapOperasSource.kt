package com.gmribas.globoplaydesafiomobile.feature.home.data.source

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.SoapOpera
import kotlinx.coroutines.flow.Flow

interface DiscoverSoapOperasSource {

    suspend fun discoverSoapOperas(): Flow<PagingData<SoapOpera>>
}