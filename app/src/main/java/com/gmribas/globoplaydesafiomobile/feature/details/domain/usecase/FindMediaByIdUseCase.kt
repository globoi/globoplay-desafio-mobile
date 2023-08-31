package com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase

import com.gmribas.globoplaydesafiomobile.core.domain.CommonUseCase
import com.gmribas.globoplaydesafiomobile.core.domain.model.MediaDetails
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShowDetails
import com.gmribas.globoplaydesafiomobile.feature.mylist.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FindMediaByIdUseCase(private val repository: MediaRepository): CommonUseCase<FindMediaByIdUseCase.Request, FindMediaByIdUseCase.Response>() {

    data class Request(val id: Int): CommonUseCase.Request
    class Response(val mediaDetails: MediaDetails?): CommonUseCase.Response

    override suspend fun process(request: Request): Flow<Response> {
        return repository.findById(request.id).map { Response(it) }
    }
}