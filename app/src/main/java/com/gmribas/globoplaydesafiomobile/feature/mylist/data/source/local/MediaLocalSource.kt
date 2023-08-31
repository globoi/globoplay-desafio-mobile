package com.gmribas.globoplaydesafiomobile.feature.mylist.data.source.local

import com.gmribas.globoplaydesafiomobile.core.data.database.dao.MediaDAO
import com.gmribas.globoplaydesafiomobile.core.domain.model.MediaDetails
import com.gmribas.globoplaydesafiomobile.core.domain.model.MovieDetails
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShowDetails
import com.gmribas.globoplaydesafiomobile.feature.mylist.data.source.MediaSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MediaLocalSource(private val dao: MediaDAO): MediaSource {
    override suspend fun getAllMediaSaved(): Flow<List<MediaDetails>> {
        return flow {
            emit(dao.getAllMedia().map { it.toDomain() })
        }
    }

    override suspend fun findById(id: Int): Flow<MediaDetails?> {
        return flow {
            emit(dao.findById(id)?.toDomain())
        }
    }

    override suspend fun saveMovie(movie: MovieDetails): Flow<Long> {
        return flow {
            emit(dao.saveMedia(movie.toMediaEntity()))
        }
    }

    override suspend fun saveTvShow(show: TvShowDetails): Flow<Long> {
        return flow {
            emit(dao.saveMedia(show.toMediaEntity()))
        }
    }

    override suspend fun remove(id: Int): Flow<Int> {
        return flow {
            emit(dao.removeMedia(id))
        }
    }
}