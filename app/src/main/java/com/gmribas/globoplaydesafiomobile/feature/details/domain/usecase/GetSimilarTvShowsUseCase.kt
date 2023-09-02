package com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gmribas.globoplaydesafiomobile.core.domain.CommonUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.repository.GetSimilarTvShowsRepository
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSimilarTvShowsUseCase(private val repository: GetSimilarTvShowsRepository): CommonUseCase<GetSimilarTvShowsUseCase.Request, GetSimilarTvShowsUseCase.Response>() {

    data class Request(val scope: CoroutineScope, val id: Int): CommonUseCase.Request

    data class Response(val tvShows: PagingData<TvShow>): CommonUseCase.Response

    override suspend fun process(request: Request): Flow<Response> {
        return repository
            .getSimilarTvShows(request.id)
            .cachedIn(request.scope)
            .map { Response(it) }
    }
}