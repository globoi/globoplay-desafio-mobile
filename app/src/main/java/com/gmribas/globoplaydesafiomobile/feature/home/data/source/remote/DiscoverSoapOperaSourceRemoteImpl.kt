package com.gmribas.globoplaydesafiomobile.feature.home.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.data.network.ApiService
import com.gmribas.globoplaydesafiomobile.core.exception.UseCaseException
import com.gmribas.globoplaydesafiomobile.feature.home.data.source.DiscoverSoapOperasSource
import com.gmribas.globoplaydesafiomobile.feature.home.data.source.remote.pagging.DiscoverSoapOperasPagingSource
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.SoapOpera
import kotlinx.coroutines.flow.Flow

class DiscoverSoapOperaSourceRemoteImpl(private val apiService: ApiService): DiscoverSoapOperasSource {

    override suspend fun discoverSoapOperas(): Flow<PagingData<SoapOpera>> {
        return try {
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false,
                    initialLoadSize = 20,
                    prefetchDistance = 3
                ),
                pagingSourceFactory = {
                    DiscoverSoapOperasPagingSource(apiService = apiService)
                }).flow
        } catch (e: AssertionError) {
            throw UseCaseException.createFromThrowable(e)
        }
    }
}