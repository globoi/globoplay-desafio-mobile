package br.com.favorites.domain.usecase

import br.com.common.domain.model.Movie
import br.com.favorites.domain.repository.FavoritesMoviesRepository
import javax.inject.Inject



class AddFavoriteMovieUseCase  @Inject constructor(
    private val repository: FavoritesMoviesRepository
) {
    suspend  operator fun invoke(movie: Int) = repository.addMovieFavorite(movie)
}