package com.gmribas.globoplaydesafiomobile.feature.home.domain.repository

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.data.dto.MovieDTO
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.SoapOpera
import kotlinx.coroutines.flow.Flow

interface GetTopRatedTvShowsRepository {

    suspend fun getTopRatedTvShows(): Flow<PagingData<SoapOpera>>
}