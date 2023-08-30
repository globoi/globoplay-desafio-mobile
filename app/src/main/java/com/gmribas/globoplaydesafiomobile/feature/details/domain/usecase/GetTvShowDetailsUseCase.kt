package com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase

import com.gmribas.globoplaydesafiomobile.core.domain.CommonUseCase
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShowDetails
import com.gmribas.globoplaydesafiomobile.feature.details.domain.repository.GetTvShowDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTvShowDetailsUseCase(private val repository: GetTvShowDetailsRepository): CommonUseCase<GetTvShowDetailsUseCase.Request, GetTvShowDetailsUseCase.Response>() {

    data class Request(val id: Int): CommonUseCase.Request

    data class Response(val details: TvShowDetails): CommonUseCase.Response

    override suspend fun process(request: Request): Flow<Response> {
        return repository.getTvShowDetails(request.id).map { Response(it) }
    }
}