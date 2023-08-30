package com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.domain.CommonUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.repository.GetSimilarTvShowsRepository
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.SoapOpera
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSimilarTvShowsUseCase(private val repository: GetSimilarTvShowsRepository): CommonUseCase<GetSimilarTvShowsUseCase.Request, GetSimilarTvShowsUseCase.Response>() {

    data class Request(val id: Int): CommonUseCase.Request

    data class Response(val tvShows: PagingData<SoapOpera>): CommonUseCase.Response

    override suspend fun process(request: Request): Flow<Response> {
        return repository.getSimilarTvShows(request.id).map { Response(it) }
    }
}