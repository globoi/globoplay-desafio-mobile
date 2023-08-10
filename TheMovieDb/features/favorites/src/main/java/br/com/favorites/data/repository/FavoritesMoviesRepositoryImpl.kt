package br.com.favorites.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import br.com.common.domain.model.Movie
import br.com.favorites.data.repository.mediator.FavoritiesRemoteMediator
import br.com.favorites.domain.mappers.FavoritesMoviesEntityToDomain
import br.com.favorites.domain.mappers.FavoritiesMoviesDtoToEntityMapper
import br.com.favorites.domain.repository.FavoritesMoviesRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val NETWORK_PAGE_SIZE = 20
private const val INITIAL_LOAD_SIZE = 40
private const val PREFETCH_DISTANCE = 6

class FavoritesMoviesRepositoryImpl @Inject constructor(
    private val favoritesRemoteDataSource: br.com.favorites.data.repository.datasource.FavoritiesRemoteDataSource,
    private val favoritesLocalDataSource: br.com.favorites.data.repository.datasource.FavoritiesLocalDataSource,
    private val remoteToLocalMapper : FavoritesMoviesEntityToDomain,
    private val localMapper: FavoritiesMoviesDtoToEntityMapper,

    ) : FavoritesMoviesRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getFavoritiesMovies(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
            initialLoadSize = INITIAL_LOAD_SIZE,
        ),
        remoteMediator = FavoritiesRemoteMediator(favoritesRemoteDataSource, favoritesLocalDataSource, localMapper),
        pagingSourceFactory = { favoritesLocalDataSource.getPagingSourceFromDb() }
    ).flow.map { pagingData->
        pagingData.map {
            remoteToLocalMapper.map(it)
        }
    }

    override suspend fun checkIsMovieSaved(movieId: Int): Flow<Boolean> = favoritesLocalDataSource.checkIsMovieSaved(movieId)

}