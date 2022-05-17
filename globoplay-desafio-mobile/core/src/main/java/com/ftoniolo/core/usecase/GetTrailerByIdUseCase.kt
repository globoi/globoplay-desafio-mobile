package com.ftoniolo.core.usecase

import com.ftoniolo.core.data.repository.FilmsRepository
import com.ftoniolo.core.domain.model.Trailer
import com.ftoniolo.core.usecase.base.ResultStatus
import com.ftoniolo.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetTrailerByIdUseCase {

    operator fun invoke(params: Params): Flow<ResultStatus<List<Trailer>>>

    data class Params(val filmId: Long)
}

class GetTrailerByIdUseCaseImpl @Inject constructor(
    private val repository: FilmsRepository
): GetTrailerByIdUseCase, UseCase<GetTrailerByIdUseCase.Params, List<Trailer>>(){
    override suspend fun doWork(params: GetTrailerByIdUseCase.Params): ResultStatus<List<Trailer>> {
        val trailer = repository.getTrailerById(params.filmId)
        return ResultStatus.Success(trailer)
    }
}