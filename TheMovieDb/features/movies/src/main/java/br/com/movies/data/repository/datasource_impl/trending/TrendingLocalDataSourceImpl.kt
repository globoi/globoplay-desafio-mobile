package br.com.movies.data.repository.datasource_impl.trending

import androidx.paging.LoadType
import androidx.paging.PagingSource
import br.com.local.dao.trending.TrendingMovieRemoteKeysDao
import br.com.local.dao.trending.TrendingMoviesDao
import br.com.local.model.trending.TrendingMovieEntity
import br.com.local.model.trending.TrendingMovieRemoteKeyEntity
import br.com.movies.data.repository.datasource.trending.TrendingLocalDataSource
import javax.inject.Inject

class TrendingLocalDataSourceImpl @Inject constructor(
    private val trendingMoviesDao: TrendingMoviesDao,
    private val trendingMoviesRemoteKeys : TrendingMovieRemoteKeysDao
) : TrendingLocalDataSource {

    override fun getPagingSourceFromDb(): PagingSource<Int, TrendingMovieEntity>  = trendingMoviesDao.getPagingSource()

    override suspend fun insertAllMoviesToDb(list: List<TrendingMovieEntity>) {
        trendingMoviesDao.upsertAll(list)
    }

    override suspend fun clearAllMoviesFromDb() {
        trendingMoviesDao.clearAll()
    }

    override suspend fun refreshDataForPaging(
        loadType: LoadType,
        page: Int,
        movies: List<TrendingMovieEntity>
    ) {
        trendingMoviesDao.deleteAndInsertTransactionForPaging(loadType, page, movies, trendingMoviesRemoteKeys)
    }

    override suspend fun insertAllRemoteKeysToDb(list: List<TrendingMovieRemoteKeyEntity>) {
        trendingMoviesRemoteKeys.upsertAll(list)
    }

    override suspend fun getRemoteKeyFromDb(movieId: Int): TrendingMovieRemoteKeyEntity? = trendingMoviesRemoteKeys.getRemoteKey(movieId)


    override suspend fun clearAllRemoteKeysFromDb() {
        trendingMoviesRemoteKeys.clearAll()
    }

}