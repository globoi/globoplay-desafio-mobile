package com.gmribas.globoplaydesafiomobile.feature.details.data.repository

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.feature.details.data.source.GetSimilarTvShowsSource
import com.gmribas.globoplaydesafiomobile.feature.details.domain.repository.GetSimilarTvShowsRepository
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.SoapOpera
import kotlinx.coroutines.flow.Flow

class GetSimilarTvShowsRepositoryImpl(private val source: GetSimilarTvShowsSource): GetSimilarTvShowsRepository {
    override suspend fun getSimilarTvShows(id: Int): Flow<PagingData<SoapOpera>> {
        return source.getSimilarSoapOperas(id)
    }
}