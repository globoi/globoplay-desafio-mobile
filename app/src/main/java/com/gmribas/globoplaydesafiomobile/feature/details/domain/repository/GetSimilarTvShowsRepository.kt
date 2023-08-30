package com.gmribas.globoplaydesafiomobile.feature.details.domain.repository

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.feature.details.domain.model.MovieDetails
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.SoapOpera
import kotlinx.coroutines.flow.Flow

interface GetSimilarTvShowsRepository {
    suspend fun getSimilarTvShows(id: Int): Flow<PagingData<SoapOpera>>
}