package com.gmribas.globoplaydesafiomobile.feature.details.domain.repository

import com.gmribas.globoplaydesafiomobile.feature.details.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails>
}