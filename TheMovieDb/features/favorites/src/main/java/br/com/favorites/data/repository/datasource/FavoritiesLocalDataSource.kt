package br.com.favorites.data.repository.datasource

import androidx.paging.LoadType
import androidx.paging.PagingSource
import br.com.local.model.favorite.FavoritiesMovieEntity
import br.com.local.model.favorite.FavoritiesMoviesRemoteKeyEntity
import br.com.local.model.movie_details.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow

interface FavoritiesLocalDataSource {

    fun getPagingSourceFromDb() : PagingSource<Int,
            FavoritiesMovieEntity>

    suspend fun insertAllMoviesToDb(list: List<FavoritiesMovieEntity>)

    suspend fun addMovie(movie: FavoritiesMovieEntity): Long


    suspend fun removeMovie(movieId: Int)

    suspend fun getMovie(movieId: Int) : FavoritiesMovieEntity
    suspend fun getMovieDetail(movieId: Int) : MovieDetailsEntity

    suspend fun clearAllMoviesFromDb()

    suspend fun refreshDataForPaging(loadType: LoadType, page: Int,
                                     movies: List<FavoritiesMovieEntity>)

    suspend fun insertAllRemoteKeysToDb(list: List<FavoritiesMoviesRemoteKeyEntity>)

    suspend fun getRemoteKeyFromDb(movieId: Int) :
            FavoritiesMoviesRemoteKeyEntity?

    suspend fun clearAllRemoteKeysFromDb()

    suspend fun checkIsMovieSaved(movieId: Int): Flow<Boolean>

}