package com.gmribas.globoplaydesafiomobile.feature.mylist.data.source.local

import com.gmribas.globoplaydesafiomobile.core.data.database.dao.MediaDAO
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShow
import com.gmribas.globoplaydesafiomobile.feature.mylist.data.source.MediaSource
import com.gmribas.globoplaydesafiomobile.feature.mylist.domain.model.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MediaLocalSource(private val dao: MediaDAO): MediaSource {
    override suspend fun getAllMediaSaved(): Flow<List<Media>> {
        return flow {
            emit(dao.getAllMedia().map { it.toDomain() })
        }
    }

    override suspend fun saveMovie(movie: Movie): Flow<Long> {
        return flow {
            emit(dao.saveMedia(movie.toMedia().toMediaEntity()))
        }
    }

    override suspend fun saveTvShow(show: TvShow): Flow<Long> {
        return flow {
            emit(dao.saveMedia(show.toMedia().toMediaEntity()))
        }
    }

    override suspend fun remove(id: Int): Flow<Int> {
        return flow {
            emit(dao.removeMedia(id))
        }
    }
}