package com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase

import com.gmribas.globoplaydesafiomobile.core.domain.CommonUseCase
import com.gmribas.globoplaydesafiomobile.core.domain.model.MovieDetails
import com.gmribas.globoplaydesafiomobile.feature.details.domain.repository.GetMovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMovieDetailsUseCase(private val repository: GetMovieDetailsRepository): CommonUseCase<GetMovieDetailsUseCase.Request, GetMovieDetailsUseCase.Response>() {

    data class Request(val movieId: Int): CommonUseCase.Request

    data class Response(val movieDetails: MovieDetails): CommonUseCase.Response

    override suspend fun process(request: Request): Flow<Response> {
        return repository.getMovieDetails(request.movieId).map { Response(it) }
    }
}