package com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gmribas.globoplaydesafiomobile.core.domain.CommonUseCase
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.feature.home.domain.repository.DiscoverMoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DiscoverMoviesUseCase(
    private val repository: DiscoverMoviesRepository
): CommonUseCase<DiscoverMoviesUseCase.Request, DiscoverMoviesUseCase.Response>() {

    class Request(val scope: CoroutineScope) : CommonUseCase.Request

    data class Response(val data: PagingData<Movie>): CommonUseCase.Response
    override suspend fun process(request: Request): Flow<Response> {
        return repository
            .discoverMovies()
            .cachedIn(request.scope)
            .map { Response(it) }
    }
}