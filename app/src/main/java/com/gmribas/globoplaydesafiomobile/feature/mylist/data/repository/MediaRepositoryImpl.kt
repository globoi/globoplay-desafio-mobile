package com.gmribas.globoplaydesafiomobile.feature.mylist.data.repository

import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShow
import com.gmribas.globoplaydesafiomobile.feature.mylist.data.source.MediaSource
import com.gmribas.globoplaydesafiomobile.feature.mylist.domain.model.Media
import com.gmribas.globoplaydesafiomobile.feature.mylist.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow

class MediaRepositoryImpl(private val source: MediaSource): MediaRepository {
    override suspend fun getAllMediaSaved(): Flow<List<Media>> {
        return source.getAllMediaSaved()
    }

    override suspend fun saveMovie(movie: Movie): Flow<Long> {
        return source.saveMovie(movie)
    }

    override suspend fun saveTvShow(show: TvShow): Flow<Long> {
        return source.saveTvShow(show)
    }

    override suspend fun remove(id: Int): Flow<Int> {
        return source.remove(id)
    }
}