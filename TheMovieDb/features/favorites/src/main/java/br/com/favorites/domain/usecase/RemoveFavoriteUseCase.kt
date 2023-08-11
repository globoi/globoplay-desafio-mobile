package br.com.favorites.domain.usecase

import br.com.favorites.domain.repository.FavoritesMoviesRepository
import javax.inject.Inject

class RemoveFavoriteUseCase  @Inject constructor(
    private val repository: FavoritesMoviesRepository
) {
    suspend  operator fun invoke(movie: Int) = repository.removeMovieFavorite(movie)
}