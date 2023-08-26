package com.gmribas.globoplaydesafiomobile.feature.home.data.source

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.data.dto.MovieDTO
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface DiscoverMoviesSource {

    suspend fun discoverMovies(): Flow<PagingData<Movie>>
}