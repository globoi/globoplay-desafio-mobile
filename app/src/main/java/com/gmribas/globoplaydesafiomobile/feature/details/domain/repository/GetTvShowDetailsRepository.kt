package com.gmribas.globoplaydesafiomobile.feature.details.domain.repository

import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShowDetails
import kotlinx.coroutines.flow.Flow

interface GetTvShowDetailsRepository {
    suspend fun getTvShowDetails(id: Int): Flow<TvShowDetails>
}