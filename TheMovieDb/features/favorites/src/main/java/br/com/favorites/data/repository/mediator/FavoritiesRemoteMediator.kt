package br.com.favorites.data.repository.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import br.com.favorites.data.repository.datasource.FavoritiesLocalDataSource
import br.com.favorites.data.repository.datasource.FavoritiesRemoteDataSource
import br.com.favorites.domain.mappers.FavoritiesMoviesDtoToEntityMapper
import br.com.local.model.favorite.FavoritiesMovieEntity
import br.com.local.model.favorite.FavoritiesMoviesRemoteKeyEntity
import br.com.network.BuildConfig
import javax.inject.Inject

const val BASE_VALUE = 10

@OptIn(ExperimentalPagingApi::class)
class FavoritiesRemoteMediator @Inject constructor(
    private val favoritiesRemoteDataSource: FavoritiesRemoteDataSource,
    private val favoritiesLocalDataSource: FavoritiesLocalDataSource,
    private val remoteToLocalMapper: FavoritiesMoviesDtoToEntityMapper,
) : RemoteMediator<Int, FavoritiesMovieEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, FavoritiesMovieEntity>
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

        favoritiesRemoteDataSource.getFavorities(
            authorization = BuildConfig.ACCESS_TOKEN_AUTH,
            page = page,
            account = BuildConfig.ACCOUNT_NUMBER
        ).onSuccess { trendingDto ->
            val movies = trendingDto.results.mapIndexed { index, movie ->
               remoteToLocalMapper.map(movie).copy(order = page.minus(1) * BASE_VALUE + index)
            }
            favoritiesLocalDataSource.refreshDataForPaging(loadType, page, movies)
            return MediatorResult.Success(endOfPaginationReached = movies.size < state.config.pageSize)
        }.onFailure {
            return MediatorResult.Error(it)
        }
        return MediatorResult.Error(Exception("Unexpected error"))
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, FavoritiesMovieEntity>
    ): FavoritiesMoviesRemoteKeyEntity? = state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
        ?.let { movie ->
            favoritiesLocalDataSource.getRemoteKeyFromDb(movie.id)
        }

}