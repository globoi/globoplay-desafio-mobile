package com.ftoniolo.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ftoniolo.core.data.repository.FilmsRepository
import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.core.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetFilmsUseCase {
    operator fun invoke(params: GetFilmsParams): Flow<PagingData<Film>>

    data class GetFilmsParams(val pagingConfig: PagingConfig)
}

class GetFilmsUseCaseImpl @Inject constructor(
    private val filmsRepository: FilmsRepository
) : PagingUseCase<GetFilmsUseCase.GetFilmsParams, Film>(),
GetFilmsUseCase {

    override fun createFlowObservable(params: GetFilmsUseCase.GetFilmsParams
    ): Flow<PagingData<Film>> {
        val pagingData = filmsRepository.getFilms()
        return Pager(config = params.pagingConfig) {
            pagingData
        }.flow
    }

}

