package com.ftoniolo.core.usecase

import com.ftoniolo.core.data.repository.FavoritesRepository
import com.ftoniolo.core.domain.model.FilmFavorite
import com.ftoniolo.core.usecase.base.CoroutinesDispatchers
import com.ftoniolo.core.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetFavoritesUseCase {
    suspend operator fun invoke(params: Unit = Unit): Flow<List<FilmFavorite>>
}

class GetFavoritesUseCaseImpl @Inject constructor(
    private val repository: FavoritesRepository,
    private val dispatchers: CoroutinesDispatchers
) : FlowUseCase<Unit, List<FilmFavorite>>(),
    GetFavoritesUseCase {
    override suspend fun createFlowObservable(params: Unit): Flow<List<FilmFavorite>> {
        return withContext(dispatchers.io()){
            repository.getAll()
        }
    }
}
