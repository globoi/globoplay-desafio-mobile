package com.gmribas.globoplaydesafiomobile.feature.home.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.data.network.ApiService
import com.gmribas.globoplaydesafiomobile.core.exception.UseCaseException
import com.gmribas.globoplaydesafiomobile.feature.home.data.source.GetTopRatedTvShowsSource
import com.gmribas.globoplaydesafiomobile.feature.home.data.source.remote.pagging.GetTopRatedTvShowsPagingSource
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

class GetTopRatedTvShowsSourceRemoteImpl(private val apiService: ApiService): GetTopRatedTvShowsSource {

    override suspend fun getTopRatedTvShows(): Flow<PagingData<TvShow>> {
        return try {
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false,
                    initialLoadSize = 20,
                    prefetchDistance = 3
                ),
                pagingSourceFactory = {
                    GetTopRatedTvShowsPagingSource(apiService = apiService)
                }).flow
        } catch (e: AssertionError) {
            throw UseCaseException.createFromThrowable(e)
        }
    }
}