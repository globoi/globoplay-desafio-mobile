package br.com.movies.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import br.com.movies.data.repository.datasource.LocalDataSource
import br.com.movies.data.repository.datasource.RemoteDataSource
import br.com.movies.data.repository.mediator.RemoteMediator
import br.com.movies.domain.mappers.TrendingMoviesDtoToEntityMapper
import br.com.movies.domain.mappers.TrendingMoviesEntityToDomain
import br.com.movies.domain.model.TrendingMovies
import br.com.movies.domain.repository.TrendingMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
private const val NETWORK_PAGE_SIZE = 20
private const val INITIAL_LOAD_SIZE = 40
private const val PREFETCH_DISTANCE = 6

class TrendingMoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val remoteToLocalMapper :TrendingMoviesEntityToDomain,
    private val localMapper: TrendingMoviesDtoToEntityMapper,

    ) : TrendingMoviesRepository{

    @OptIn(ExperimentalPagingApi::class)
    override fun getTrendingMovies(): Flow<PagingData<TrendingMovies>> = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
            initialLoadSize = INITIAL_LOAD_SIZE,
        ),
        remoteMediator = RemoteMediator(remoteDataSource, localDataSource, localMapper),
        pagingSourceFactory = { localDataSource.getPagingSourceFromDb() }
    ).flow.map { pagingData->
        pagingData.map {
                remoteToLocalMapper.map(it)
        }
    }
}