package com.gmribas.globoplaydesafiomobile.feature.details.data.source.remote

import com.gmribas.globoplaydesafiomobile.core.data.network.ApiService
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShowDetails
import com.gmribas.globoplaydesafiomobile.feature.details.data.source.GetTvShowDetailsSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTvShowDetailsSourceRemoteImpl(private val service: ApiService): GetTvShowDetailsSource {
    override suspend fun getTvShowDetails(id: Int): Flow<TvShowDetails> {
        return flow {
            emit(
                service.getTvShowDetails(id).toDomain()
            )
        }
    }
}