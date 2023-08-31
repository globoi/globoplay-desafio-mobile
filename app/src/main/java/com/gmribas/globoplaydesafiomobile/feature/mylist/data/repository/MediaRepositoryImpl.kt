package com.gmribas.globoplaydesafiomobile.feature.mylist.data.repository

import com.gmribas.globoplaydesafiomobile.core.domain.model.MediaDetails
import com.gmribas.globoplaydesafiomobile.core.domain.model.MovieDetails
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShowDetails
import com.gmribas.globoplaydesafiomobile.feature.mylist.data.source.MediaSource
import com.gmribas.globoplaydesafiomobile.feature.mylist.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow

class MediaRepositoryImpl(private val source: MediaSource): MediaRepository {
    override suspend fun getAllMediaSaved(): Flow<List<MediaDetails>> {
        return source.getAllMediaSaved()
    }

    override suspend fun findById(id: Int): Flow<MediaDetails?> {
        return source.findById(id)
    }

    override suspend fun saveMovie(movie: MovieDetails): Flow<Long> {
        return source.saveMovie(movie)
    }

    override suspend fun saveTvShow(show: TvShowDetails): Flow<Long> {
        return source.saveTvShow(show)
    }

    override suspend fun remove(id: Int): Flow<Int> {
        return source.remove(id)
    }
}