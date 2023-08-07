package br.com.movies.data.repository.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import br.com.local.model.TrendingMovieEntity
import br.com.local.model.TrendingMovieRemoteKeyEntity
import br.com.movies.data.repository.datasource.LocalDataSource
import br.com.movies.data.repository.datasource.RemoteDataSource
import br.com.movies.domain.mappers.TrendingMoviesDtoToEntityMapper
import javax.inject.Inject

const val BASE_VALUE = 10

@OptIn(ExperimentalPagingApi::class)
class RemoteMediator @Inject constructor(
    private val trendingRemoteDataSource: RemoteDataSource,
    private val trendingLocalDataSource: LocalDataSource,
    private val remoteToLocalMapper: TrendingMoviesDtoToEntityMapper,
) : RemoteMediator<Int, TrendingMovieEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TrendingMovieEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> null
            LoadType.PREPEND -> return MediatorResult.Success(
                endOfPaginationReached = true
            )
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                remoteKeys?.nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        } ?: 1

        trendingRemoteDataSource.getTrendingMovies(
            page = page
        ).onSuccess { trendingDto ->
            val movies = trendingDto.results.mapIndexed { index, movie ->
                /* It is required to keep order of items because it
                   shows trending movies and the order is important.
                   Otherwise, Room orders items by their ids */
                remoteToLocalMapper.map(movie).copy(order = page.minus(1) * BASE_VALUE + index)
            }
            trendingLocalDataSource.refreshDataForPaging(loadType, page, movies)
            return MediatorResult.Success(endOfPaginationReached = movies.size < state.config.pageSize)
        }.onFailure {
            return MediatorResult.Error(it)
        }
        return MediatorResult.Error(Exception("Unexpected error"))
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, TrendingMovieEntity>
    ): TrendingMovieRemoteKeyEntity? = state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
        ?.let { movie ->
            trendingLocalDataSource.getRemoteKeyFromDb(movie.id)
        }

}