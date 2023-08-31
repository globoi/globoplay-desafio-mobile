package com.gmribas.globoplaydesafiomobile.feature.mylist.domain.repository

import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShow
import com.gmribas.globoplaydesafiomobile.feature.mylist.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface MediaRepository {

    suspend fun getAllMediaSaved(): Flow<List<Media>>

    suspend fun saveMovie(movie: Movie): Flow<Long>

    suspend fun saveTvShow(show: TvShow): Flow<Long>

    suspend fun remove(id: Int): Flow<Int>
}