package br.com.favorites.domain.usecase

import androidx.paging.PagingData
import br.com.common.domain.model.Movie
import br.com.favorites.domain.repository.FavoritesMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesMoviesUseCase @Inject constructor(
    private val repository: FavoritesMoviesRepository
) {
    operator fun invoke() : Flow<PagingData<Movie>> = repository.getFavoritiesMovies()
}