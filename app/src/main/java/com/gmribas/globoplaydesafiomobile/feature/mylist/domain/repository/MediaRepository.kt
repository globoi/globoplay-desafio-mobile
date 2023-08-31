package com.gmribas.globoplaydesafiomobile.feature.mylist.domain.repository

import com.gmribas.globoplaydesafiomobile.core.domain.model.MediaDetails
import com.gmribas.globoplaydesafiomobile.core.domain.model.MovieDetails
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShowDetails
import kotlinx.coroutines.flow.Flow

interface MediaRepository {

    suspend fun getAllMediaSaved(): Flow<List<MediaDetails>>

    suspend fun findById(id: Int): Flow<MediaDetails?>

    suspend fun saveMovie(movie: MovieDetails): Flow<Long>

    suspend fun saveTvShow(show: TvShowDetails): Flow<Long>

    suspend fun remove(id: Int): Flow<Int>
}