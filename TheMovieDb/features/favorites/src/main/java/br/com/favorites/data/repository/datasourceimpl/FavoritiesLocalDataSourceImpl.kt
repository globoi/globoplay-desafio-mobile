package br.com.favorites.data.repository.datasourceimpl

import androidx.paging.LoadType
import androidx.paging.PagingSource
import br.com.local.dao.favorities.FavoritiesMovieRemoteKeysDao
import br.com.local.dao.favorities.FavoritiesMoviesDao
import br.com.local.model.favorite.FavoritiesMovieEntity
import br.com.local.model.favorite.FavoritiesMoviesRemoteKeyEntity
import br.com.favorites.data.repository.datasource.FavoritiesLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritiesLocalDataSourceImpl @Inject constructor(
    private val favoritiesMovies: FavoritiesMoviesDao,
    private val favoritiesMoviesRemoteKeys: FavoritiesMovieRemoteKeysDao
) : br.com.favorites.data.repository.datasource.FavoritiesLocalDataSource {
    override fun getPagingSourceFromDb(): PagingSource<Int, FavoritiesMovieEntity> = favoritiesMovies.getPagingSource()

    override suspend fun insertAllMoviesToDb(list: List<FavoritiesMovieEntity>) {
       favoritiesMovies.upsertAll(list)
    }

    override suspend fun clearAllMoviesFromDb() {
        favoritiesMovies.clearAll()
    }

    override suspend fun refreshDataForPaging(
        loadType: LoadType,
        page: Int,
        movies: List<FavoritiesMovieEntity>
    ) {
        favoritiesMovies.deleteAndInsertTransactionForPaging(loadType, page, movies, favoritiesMoviesRemoteKeys)

    }

    override suspend fun insertAllRemoteKeysToDb(list: List<FavoritiesMoviesRemoteKeyEntity>) {
       favoritiesMoviesRemoteKeys.upsertAll(list)
    }

    override suspend fun getRemoteKeyFromDb(movieId: Int): FavoritiesMoviesRemoteKeyEntity? = favoritiesMoviesRemoteKeys.getRemoteKey(movieId)

    override suspend fun clearAllRemoteKeysFromDb() {
        favoritiesMoviesRemoteKeys.clearAll()
    }

    override suspend fun checkIsMovieSaved(movieId: Int): Flow<Boolean> = favoritiesMovies.checkIsMovieSaved(movieId)


}