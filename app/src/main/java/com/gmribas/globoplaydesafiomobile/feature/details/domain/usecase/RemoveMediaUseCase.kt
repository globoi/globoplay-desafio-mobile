package com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase

import com.gmribas.globoplaydesafiomobile.core.domain.CommonUseCase
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.feature.mylist.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RemoveMediaUseCase(private val repository: MediaRepository): CommonUseCase<RemoveMediaUseCase.Request, RemoveMediaUseCase.Response>() {

    data class Request(val id: Int): CommonUseCase.Request
    class Response(val removed: Int): CommonUseCase.Response

    override suspend fun process(request: Request): Flow<Response> {
        return repository.remove(request.id).map { Response(it) }
    }
}