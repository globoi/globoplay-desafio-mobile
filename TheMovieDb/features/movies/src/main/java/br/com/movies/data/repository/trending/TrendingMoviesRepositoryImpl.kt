package br.com.movies.data.repository.trending

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import br.com.movies.data.repository.datasource.trending.TrendingLocalDataSource
import br.com.movies.data.repository.datasource.trending.TrendingRemoteDataSource
import br.com.movies.data.repository.mediator.trending.TrendingRemoteMediator
import br.com.movies.domain.mappers.trending.TrendingMoviesDtoToEntityMapper
import br.com.movies.domain.mappers.trending.TrendingMoviesEntityToDomain
import br.com.common.domain.model.Movie
import br.com.movies.domain.repository.trending.TrendingMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
private const val NETWORK_PAGE_SIZE = 20
private const val INITIAL_LOAD_SIZE = 40
private const val PREFETCH_DISTANCE = 6

class TrendingMoviesRepositoryImpl @Inject constructor(
    private val trendingRemoteDataSource: TrendingRemoteDataSource,
    private val trendingLocalDataSource: TrendingLocalDataSource,
    private val remoteToLocalMapper : TrendingMoviesEntityToDomain,
    private val localMapper: TrendingMoviesDtoToEntityMapper,

    ) : TrendingMoviesRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getTrendingMovies(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
            initialLoadSize = INITIAL_LOAD_SIZE,
        ),
        remoteMediator = TrendingRemoteMediator(trendingRemoteDataSource, trendingLocalDataSource, localMapper),
        pagingSourceFactory = { trendingLocalDataSource.getPagingSourceFromDb() }
    ).flow.map { pagingData->
        pagingData.map {
                remoteToLocalMapper.map(it)
        }
    }
}