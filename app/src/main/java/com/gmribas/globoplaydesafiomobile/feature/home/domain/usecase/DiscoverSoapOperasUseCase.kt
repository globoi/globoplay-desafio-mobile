package com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gmribas.globoplaydesafiomobile.core.domain.CommonUseCase
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.SoapOpera
import com.gmribas.globoplaydesafiomobile.feature.home.domain.repository.DiscoverSoapOperasRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DiscoverSoapOperasUseCase(
    private val repository: DiscoverSoapOperasRepository
): CommonUseCase<DiscoverSoapOperasUseCase.Request, DiscoverSoapOperasUseCase.Response>() {

    class Request(val scope: CoroutineScope) : CommonUseCase.Request

    data class Response(val data: PagingData<SoapOpera>): CommonUseCase.Response
    override suspend fun process(request: Request): Flow<Response> {
        return repository
            .discoverSoapOperas()
            .cachedIn(request.scope)
            .map { Response(it) }
    }
}