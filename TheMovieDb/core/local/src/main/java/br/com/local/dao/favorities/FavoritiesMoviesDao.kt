package br.com.local.dao.favorities

import androidx.paging.LoadType
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(favoriteMovie: FavoritiesMovieEntity): Long

    @Query("SELECT * FROM favorities_movies ORDER BY `order` ASC")
    fun getPagingSource(): PagingSource<Int, FavoritiesMovieEntity>

    @Query("DELETE FROM favorities_movies")
    suspend fun clearAll()

    @Query("DELETE FROM favorities_movies WHERE id = :movieId")
    fun removeMovie(movieId: Int)

    @Query("SELECT EXISTS (SELECT * FROM favorities_movies WHERE id = :movieId)")
    fun checkIsMovieSaved(movieId: Int): Flow<Boolean>

    @Query("SELECT * FROM favorities_movies WHERE id = :movieId")
    fun getMovie(movieId: Int): FavoritiesMovieEntity

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