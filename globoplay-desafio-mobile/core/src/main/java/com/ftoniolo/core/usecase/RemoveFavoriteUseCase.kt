package com.ftoniolo.core.usecase

import com.ftoniolo.core.data.repository.FavoritesRepository
import com.ftoniolo.core.domain.model.FilmFavorite
import com.ftoniolo.core.usecase.base.CoroutinesDispatchers
import com.ftoniolo.core.usecase.base.ResultStatus
import com.ftoniolo.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RemoveFavoriteUseCase {

    operator fun invoke(params: Params): Flow<ResultStatus<Unit>>

        data class Params(val id: Long, val title: String, val imageUrl: String)
}

class RemoveFavoriteUseCaseImpl @Inject constructor(
        private val repository: FavoritesRepository,
        private val dispatchers: CoroutinesDispatchers
) : UseCase<RemoveFavoriteUseCase.Params, Unit>(), RemoveFavoriteUseCase {
        override suspend fun doWork(params: RemoveFavoriteUseCase.Params): ResultStatus<Unit> {
                return withContext(dispatchers.io()) {
                        repository.deleteFavorite(
                                FilmFavorite(params.id, params.title, params.imageUrl)
                        )
                        ResultStatus.Success(Unit)
                }
        }
}