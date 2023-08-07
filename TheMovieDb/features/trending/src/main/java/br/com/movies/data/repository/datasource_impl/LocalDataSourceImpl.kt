package br.com.movies.data.repository.datasource_impl

import androidx.paging.LoadType
import androidx.paging.PagingSource
import br.com.local.dao.TrendingMovieRemoteKeysDao
import br.com.local.dao.TrendingMoviesDao
import br.com.local.model.TrendingMovieEntity
import br.com.local.model.TrendingMovieRemoteKeyEntity
import br.com.movies.data.repository.datasource.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val trendingMoviesDao: TrendingMoviesDao,
    private val trendingMoviesRemoteKeys : TrendingMovieRemoteKeysDao
) : LocalDataSource {

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