package com.gmribas.globoplaydesafiomobile.feature.home.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.data.network.ApiService
import com.gmribas.globoplaydesafiomobile.core.exception.UseCaseException
import com.gmribas.globoplaydesafiomobile.feature.home.data.source.DiscoverMoviesSource
import com.gmribas.globoplaydesafiomobile.feature.home.data.source.remote.pagging.DiscoverMoviesPagingSource
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class DiscoverMoviesSourceRemoteImpl(private val apiService: ApiService): DiscoverMoviesSource {

    override suspend fun discoverMovies(): Flow<PagingData<Movie>> {
        return try {
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false,
                    initialLoadSize = 20,
                    prefetchDistance = 3
                ),
                pagingSourceFactory = {
                    DiscoverMoviesPagingSource(apiService = apiService)
                }).flow
        } catch (e: AssertionError) {
            throw UseCaseException.createFromThrowable(e)
        }
    }
}