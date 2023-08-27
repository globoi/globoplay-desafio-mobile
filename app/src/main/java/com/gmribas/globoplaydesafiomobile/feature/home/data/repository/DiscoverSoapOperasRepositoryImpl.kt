package com.gmribas.globoplaydesafiomobile.feature.home.data.repository

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.feature.home.data.source.DiscoverSoapOperasSource
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.SoapOpera
import com.gmribas.globoplaydesafiomobile.feature.home.domain.repository.DiscoverSoapOperasRepository
import kotlinx.coroutines.flow.Flow

class DiscoverSoapOperasRepositoryImpl(
    private val source: DiscoverSoapOperasSource
): DiscoverSoapOperasRepository {

    override suspend fun discoverSoapOperas(): Flow<PagingData<SoapOpera>> = source.discoverSoapOperas()
}