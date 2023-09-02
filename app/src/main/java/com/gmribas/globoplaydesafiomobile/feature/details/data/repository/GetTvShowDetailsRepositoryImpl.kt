package com.gmribas.globoplaydesafiomobile.feature.details.data.repository

import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShowDetails
import com.gmribas.globoplaydesafiomobile.feature.details.data.source.GetTvShowDetailsSource
import com.gmribas.globoplaydesafiomobile.feature.details.domain.repository.GetTvShowDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetTvShowDetailsRepositoryImpl(private val source: GetTvShowDetailsSource):
    GetTvShowDetailsRepository {
    override suspend fun getTvShowDetails(id: Int): Flow<TvShowDetails> {
        return source.getTvShowDetails(id)
    }
}