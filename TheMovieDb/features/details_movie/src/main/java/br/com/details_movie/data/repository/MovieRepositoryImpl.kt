package br.com.details_movie.data.repository

import br.com.details_movie.data.repository.datasource.MovieLocalDataSource
import br.com.details_movie.data.repository.datasource.MovieRemoteDataSource
import br.com.details_movie.domain.mappers.MovieRemoteToEntityMapper
import br.com.details_movie.domain.mappers.MovieToDomainMapper
import br.com.details_movie.domain.model.Movie
import br.com.details_movie.domain.repository.MovieRepository
import br.com.network.NetworkException
import br.com.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
    private val remoteToLocalMapper: MovieRemoteToEntityMapper,
    private val localMapper: MovieToDomainMapper,
) : MovieRepository {

    override fun getMovie(movieId: Int): Flow<Resource<Movie>> = flow {

            emit(Resource.Loading)

        val movieFromDb = getMovieFromDb(movieId)

        if (movieFromDb != null) {
            emit(Resource.Success(movieFromDb))
            emitAll(getMovieDataFlowFromRemote(movieId, false))
            return@flow
        }

        emitAll(getMovieDataFlowFromRemote(movieId))
    }

    private suspend fun getMovieFromDb(movieId: Int) : Movie? = localDataSource.getMovie(movieId)?.let { movieEntity->
        localMapper.map(movieEntity)
    }

    private suspend fun  getMovieDataFlowFromRemote(movieid:Int, emitError: Boolean = true)
    :Flow<Resource<Movie>>
    = flow {
        remoteDataSource.getMovie(movieid).onSuccess { movieDto->
            val movieEntity = remoteToLocalMapper.map(movieDto)
            val movie = localMapper.map(movieEntity)
            emit(Resource.Success(movie))
            localDataSource.insertMovieToDb(movieEntity)
        }.onFailure {
            if(emitError.not()) {
                return@onFailure
            }
            val exception = it as NetworkException
            exception ?: return@onFailure
            emit(Resource.Error(exception))
        }
    }


}