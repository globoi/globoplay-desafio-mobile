package br.com.favorites.domain.usecase

import br.com.favorites.domain.repository.FavoritesMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIsFavoriteMovieUseCase  @Inject constructor(
    private val repository: FavoritesMoviesRepository
) {
    suspend  operator fun invoke(movieId: Int) : Flow<Boolean> = repository.checkIsMovieSaved(movieId)
}