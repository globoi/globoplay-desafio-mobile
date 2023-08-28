package com.gmribas.globoplaydesafiomobile.feature.details.data.source.remote

import com.gmribas.globoplaydesafiomobile.core.data.network.ApiService
import com.gmribas.globoplaydesafiomobile.feature.details.data.source.GetMovieDetailsSource
import com.gmribas.globoplaydesafiomobile.feature.details.data.source.toDomain
import com.gmribas.globoplaydesafiomobile.feature.details.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMovieDetailsSourceRemoteImpl(private val service: ApiService): GetMovieDetailsSource {
    override suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails> {
        return flow {
            emit(
                service.getMovieDetails(movieId).toDomain()
            )
        }
    }
}