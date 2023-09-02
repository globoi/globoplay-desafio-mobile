package com.gmribas.globoplaydesafiomobile.feature.details.data.source

import com.gmribas.globoplaydesafiomobile.core.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailsSource {

    suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails>
}