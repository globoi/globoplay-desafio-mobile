package com.gmribas.globoplaydesafiomobile.feature.home.data.repository

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.feature.home.data.source.GetTopRatedTvShowsSource
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.TvShow
import com.gmribas.globoplaydesafiomobile.feature.home.domain.repository.GetTopRatedTvShowsRepository
import kotlinx.coroutines.flow.Flow

class GetTopRatedTvShowsRepositoryImpl(
    private val source: GetTopRatedTvShowsSource
): GetTopRatedTvShowsRepository {

    override suspend fun getTopRatedTvShows(): Flow<PagingData<TvShow>> = source.getTopRatedTvShows()
}