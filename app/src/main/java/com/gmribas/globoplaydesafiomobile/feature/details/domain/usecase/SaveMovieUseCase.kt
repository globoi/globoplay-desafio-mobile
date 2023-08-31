package com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase

import com.gmribas.globoplaydesafiomobile.core.domain.CommonUseCase
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.core.domain.model.MovieDetails
import com.gmribas.globoplaydesafiomobile.feature.mylist.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SaveMovieUseCase(private val repository: MediaRepository): CommonUseCase<SaveMovieUseCase.Request, SaveMovieUseCase.Response>() {

    data class Request(val movie: MovieDetails): CommonUseCase.Request
    class Response(val insert: Long): CommonUseCase.Response

    override suspend fun process(request: Request): Flow<Response> {
        return repository.saveMovie(request.movie).map { Response(it) }
    }
}