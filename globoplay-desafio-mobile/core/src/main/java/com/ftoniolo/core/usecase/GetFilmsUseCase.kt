package com.ftoniolo.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ftoniolo.core.data.repository.FilmsRepository
import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.core.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(
    private val filmsRepository: FilmsRepository
) : PagingUseCase<GetFilmsUseCase.GetFilmsParams, Film>() {

    override fun createFlowObservable(params: GetFilmsParams
    ): Flow<PagingData<Film>> {

        return Pager(config = params.pagingConfig) {
            filmsRepository.getFilms()
        }.flow
    }

    data class GetFilmsParams(val pagingConfig: PagingConfig)
}

