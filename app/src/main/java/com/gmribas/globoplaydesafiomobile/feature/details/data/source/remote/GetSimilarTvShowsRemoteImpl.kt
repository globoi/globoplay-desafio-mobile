package com.gmribas.globoplaydesafiomobile.feature.details.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.data.network.ApiService
import com.gmribas.globoplaydesafiomobile.core.exception.UseCaseException
import com.gmribas.globoplaydesafiomobile.feature.details.data.source.GetSimilarTvShowsSource
import com.gmribas.globoplaydesafiomobile.feature.details.data.source.remote.paging.GetSimilarTvShowsPagingSource
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

class GetSimilarTvShowsRemoteImpl(private val service: ApiService): GetSimilarTvShowsSource {

    override suspend fun getSimilarSoapOperas(id: Int): Flow<PagingData<TvShow>> {
        return try {
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false,
                    initialLoadSize = 20,
                    prefetchDistance = 3
                ),
                pagingSourceFactory = {
                    GetSimilarTvShowsPagingSource(apiService = service, id = id)
                }).flow
        } catch (e: AssertionError) {
            throw UseCaseException.createFromThrowable(e)
        }
    }
}