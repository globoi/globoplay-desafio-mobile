package com.gmribas.globoplaydesafiomobile.feature.details.data.source

import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShowDetails
import kotlinx.coroutines.flow.Flow

interface GetTvShowDetailsSource {

    suspend fun getTvShowDetails(id: Int): Flow<TvShowDetails>
}