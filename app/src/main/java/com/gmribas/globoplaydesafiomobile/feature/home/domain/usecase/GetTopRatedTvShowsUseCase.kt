package com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gmribas.globoplaydesafiomobile.core.domain.CommonUseCase
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShow
import com.gmribas.globoplaydesafiomobile.feature.home.domain.repository.GetTopRatedTvShowsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTopRatedTvShowsUseCase(
    private val repository: GetTopRatedTvShowsRepository
): CommonUseCase<GetTopRatedTvShowsUseCase.Request, GetTopRatedTvShowsUseCase.Response>() {

    class Request(val scope: CoroutineScope) : CommonUseCase.Request

    data class Response(val data: PagingData<TvShow>): CommonUseCase.Response
    override suspend fun process(request: Request): Flow<Response> {
        return repository
            .getTopRatedTvShows()
            .cachedIn(request.scope)
            .map { Response(it) }
    }
}