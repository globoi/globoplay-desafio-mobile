package br.com.local.dao.trending

import androidx.paging.LoadType
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import br.com.local.model.trending.TrendingMovieEntity
import br.com.local.model.trending.TrendingMovieRemoteKeyEntity

const val TMDB_FIRST_PAGE_INDEX = 1
const val LAST_PAGE = 1000

@Dao
interface TrendingMoviesDao {

    @Upsert
    suspend fun upsertAll(movieAll: List<TrendingMovieEntity>)

    @Query("SELECT * FROM trending_movies ORDER BY `order` ASC")
    fun getPagingSource(): PagingSource<Int, TrendingMovieEntity>

    @Query("DELETE FROM trending_movies")
    suspend fun clearAll()

    @Transaction
    suspend fun deleteAndInsertTransactionForPaging(
        loadType: LoadType,
        page: Int,
        movies: List<TrendingMovieEntity>,
        trendingMovieRemoteKeysDao: TrendingMovieRemoteKeysDao
    ) {
        val endOfPaginationReached = page == LAST_PAGE
        if (loadType == LoadType.REFRESH) {
            trendingMovieRemoteKeysDao.clearAll()
            clearAll()
        }
        val prevKey = page.minus(1).takeUnless { page == TMDB_FIRST_PAGE_INDEX }
        val nextKey = page.plus(1).takeUnless { endOfPaginationReached }
        val keys = movies.map {
            TrendingMovieRemoteKeyEntity(id = it.id, prevKey = prevKey, nextKey = nextKey)
        }
        trendingMovieRemoteKeysDao.upsertAll(keys)
        upsertAll(movies)
    }
}