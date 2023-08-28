package com.gmribas.globoplaydesafiomobile.feature.details.data.repository

import com.gmribas.globoplaydesafiomobile.feature.details.data.source.GetMovieDetailsSource
import com.gmribas.globoplaydesafiomobile.feature.details.domain.model.MovieDetails
import com.gmribas.globoplaydesafiomobile.feature.details.domain.repository.GetMovieDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDetailsRepositoryImpl(private val source: GetMovieDetailsSource): GetMovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails> = source.getMovieDetails(movieId)
}