package com.ftoniolo.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ftoniolo.core.data.repository.FilmsRepository
import com.ftoniolo.core.domain.model.WatchToo
import com.ftoniolo.core.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetWatchTooUseCase {

    operator fun invoke(params: GetWatchTooParams): Flow<PagingData<WatchToo>>

    data class GetWatchTooParams(val filmId: Long, val pagingConfig: PagingConfig)
}

class GetWatchTooUseCaseImpl @Inject constructor(
    private val repository: FilmsRepository
) : GetWatchTooUseCase, PagingUseCase<GetWatchTooUseCase.GetWatchTooParams, WatchToo>() {
    override fun createFlowObservable(params: GetWatchTooUseCase.GetWatchTooParams): Flow<PagingData<WatchToo>> {
        return Pager(params.pagingConfig){
            repository.getWatchToo(params.filmId)
        }.flow
    }
}

