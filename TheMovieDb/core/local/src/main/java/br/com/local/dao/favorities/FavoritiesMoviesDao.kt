package br.com.local.dao.favorities

import androidx.paging.LoadType
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import br.com.local.dao.trending.LAST_PAGE
import br.com.local.dao.trending.TMDB_FIRST_PAGE_INDEX
import br.com.local.model.favorite.FavoritiesMovieEntity
import br.com.local.model.favorite.FavoritiesMoviesRemoteKeyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritiesMoviesDao {

    @Upsert
    suspend fun upsertAll(movieAll: List<FavoritiesMovieEntity>)

    @Query("SELECT * FROM favorities_movies ORDER BY `order` ASC")
    fun getPagingSource(): PagingSource<Int, FavoritiesMovieEntity>

    @Query("DELETE FROM favorities_movies")
    suspend fun clearAll()

    @Query("SELECT EXISTS (SELECT * FROM favorities_movies WHERE id = :movieId)")
    fun checkIsMovieSaved(movieId: Int): Flow<Boolean>

    @Transaction
    suspend fun deleteAndInsertTransactionForPaging(
        loadType: LoadType,
        page: Int,
        movies: List<FavoritiesMovieEntity>,
        favoritiesMovieRemoteKeysDao: FavoritiesMovieRemoteKeysDao
    ) {
        val endOfPaginationReached = page == LAST_PAGE
        if (loadType == LoadType.REFRESH) {
            favoritiesMovieRemoteKeysDao.clearAll()
            clearAll()
        }
        val prevKey = page.minus(1).takeUnless { page == TMDB_FIRST_PAGE_INDEX }
        val nextKey = page.plus(1).takeUnless { endOfPaginationReached }
        val keys = movies.map {
            FavoritiesMoviesRemoteKeyEntity(id = it.id, prevKey = prevKey, nextKey = nextKey)
        }
        favoritiesMovieRemoteKeysDao.upsertAll(keys)
        upsertAll(movies)
    }

}