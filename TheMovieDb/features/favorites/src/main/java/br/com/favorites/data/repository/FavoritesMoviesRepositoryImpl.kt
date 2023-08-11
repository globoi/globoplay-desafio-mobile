package br.com.favorites.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import br.com.common.domain.model.Movie
import br.com.favorites.data.repository.datasource.FavoritiesLocalDataSource
import br.com.favorites.data.repository.datasource.FavoritiesRemoteDataSource
import br.com.favorites.data.repository.mediator.FavoritiesRemoteMediator
import br.com.favorites.domain.mappers.FavoritesMovieToEntityMapper
import br.com.favorites.domain.mappers.FavoritesMoviesEntityToDomain
import br.com.favorites.domain.mappers.FavoritiesMoviesDtoToEntityMapper
import br.com.favorites.domain.model.AddOrRemoveFavorite
import br.com.favorites.domain.repository.FavoritesMoviesRepository
import br.com.local.dao.movie_details.MovieDetailsDao
import br.com.local.model.MovieEntity
import br.com.local.model.favorite.FavoritiesMovieEntity

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val NETWORK_PAGE_SIZE = 20
private const val INITIAL_LOAD_SIZE = 40
private const val PREFETCH_DISTANCE = 6

class FavoritesMoviesRepositoryImpl @Inject constructor(
    private val favoritesRemoteDataSource: FavoritiesRemoteDataSource,
    private val favoritesLocalDataSource: FavoritiesLocalDataSource,
    private val moviesDao: MovieDetailsDao,
    private val movieEntityToDomainMapper : FavoritesMoviesEntityToDomain,
    private val movieDtoToEntityMapper: FavoritiesMoviesDtoToEntityMapper,
    private val movieToEntityMapper: FavoritesMovieToEntityMapper

    ) : FavoritesMoviesRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getFavoritiesMovies(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
            initialLoadSize = INITIAL_LOAD_SIZE,
        ),
        remoteMediator = FavoritiesRemoteMediator(favoritesRemoteDataSource, favoritesLocalDataSource, movieDtoToEntityMapper),
        pagingSourceFactory = { favoritesLocalDataSource.getPagingSourceFromDb() }
    ).flow.map { pagingData->
        pagingData.map {
            movieEntityToDomainMapper.map(it)
        }
    }

    override suspend fun checkIsMovieSaved(movieId: Int): Flow<Boolean> = favoritesLocalDataSource.checkIsMovieSaved(movieId)
    override suspend fun addMovieFavorite(idMovie: AddOrRemoveFavorite) {

    }

    override suspend fun removeMovieFavorite(idMovie: Int) {
      // favoritesLocalDataSource.removeMovie(idMovie)
    }

}