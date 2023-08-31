package com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase

import com.gmribas.globoplaydesafiomobile.core.domain.CommonUseCase
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShowDetails
import com.gmribas.globoplaydesafiomobile.feature.mylist.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SaveTvShowUseCase(private val repository: MediaRepository): CommonUseCase<SaveTvShowUseCase.Request, SaveTvShowUseCase.Response>() {

    data class Request(val tvShow: TvShowDetails): CommonUseCase.Request
    class Response(val insert: Long): CommonUseCase.Response

    override suspend fun process(request: Request): Flow<Response> {
        return repository.saveTvShow(request.tvShow).map { Response(it) }
    }
}