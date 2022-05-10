package com.ftoniolo.core.usecase

import com.ftoniolo.core.data.repository.FilmsRepository
import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.core.usecase.base.ResultStatus
import com.ftoniolo.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPopularFilmsUseCase {

    operator  fun invoke(params: Any): Flow<ResultStatus<List<Film>>>
}

class GetPopularFilmsUseCaseImpl @Inject constructor(
    private val repository: FilmsRepository
) : GetPopularFilmsUseCase, UseCase<Any, List<Film>>() {
    override suspend fun doWork(params: Any): ResultStatus<List<Film>> {
        val filmsPopular = repository.getPopularFilms()
        return ResultStatus.Success(filmsPopular)
    }
}

