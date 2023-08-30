package com.gmribas.globoplaydesafiomobile.feature.details.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.data.network.ApiService
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.core.exception.UseCaseException
import com.gmribas.globoplaydesafiomobile.feature.details.data.source.GetSimilarMoviesSource
import com.gmribas.globoplaydesafiomobile.feature.details.data.source.remote.paging.GetSimilarMoviesPagingSource
import kotlinx.coroutines.flow.Flow

class GetSimilarMoviesRemoteImpl(private val service: ApiService): GetSimilarMoviesSource {

    override suspend fun getSimilarMovies(movieId: Int): Flow<PagingData<Movie>> {
        return try {
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false,
                    initialLoadSize = 20,
                    prefetchDistance = 3
                ),
                pagingSourceFactory = {
                    GetSimilarMoviesPagingSource(apiService = service, movieId = movieId)
                }).flow
        } catch (e: AssertionError) {
            throw UseCaseException.createFromThrowable(e)
        }
    }
}