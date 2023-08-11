package br.com.favorites.domain.repository

import androidx.paging.PagingData
import br.com.common.domain.model.Movie
import br.com.favorites.domain.model.AddOrRemoveFavorite
import kotlinx.coroutines.flow.Flow

interface FavoritesMoviesRepository {

    fun getFavoritiesMovies() : Flow<PagingData<Movie>>

    suspend fun checkIsMovieSaved(movieId: Int): Flow<Boolean>

    suspend fun addMovieFavorite(idMovie: AddOrRemoveFavorite)

    suspend fun removeMovieFavorite(idMovie: Int)


}