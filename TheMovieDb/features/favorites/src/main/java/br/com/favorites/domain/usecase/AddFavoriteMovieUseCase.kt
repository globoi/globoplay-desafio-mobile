package br.com.favorites.domain.usecase

import br.com.common.domain.model.Movie
import br.com.favorites.domain.model.AddOrRemoveFavorite
import br.com.favorites.domain.model.ResultAddFavorite
import br.com.favorites.domain.repository.FavoritesMoviesRepository
import br.com.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject



class AddFavoriteMovieUseCase  @Inject constructor(
    private val repository: FavoritesMoviesRepository
) {
    suspend  operator fun invoke(movie: AddOrRemoveFavorite) : Flow<Resource<ResultAddFavorite>> = repository.addMovieFavorite(movie)
}