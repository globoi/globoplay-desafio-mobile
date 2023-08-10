package br.com.movies.data.repository.datasource.trending

import androidx.paging.LoadType
import androidx.paging.PagingSource
import br.com.local.model.trending.TrendingMovieEntity
import br.com.local.model.trending.TrendingMovieRemoteKeyEntity

interface TrendingLocalDataSource {
    fun getPagingSourceFromDb(): PagingSource<Int,
            TrendingMovieEntity>
    suspend fun insertAllMoviesToDb(list: List<TrendingMovieEntity>)
    suspend fun clearAllMoviesFromDb()
    suspend fun refreshDataForPaging(loadType: LoadType, page: Int,
                                     movies: List<TrendingMovieEntity>)
    suspend fun insertAllRemoteKeysToDb(list:
                                        List<TrendingMovieRemoteKeyEntity>)
    suspend fun getRemoteKeyFromDb(movieId: Int):
            TrendingMovieRemoteKeyEntity?
    suspend fun clearAllRemoteKeysFromDb()
}