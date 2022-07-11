package com.nroncari.movieplay.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nroncari.movieplay.data.localdatasource.MovieLocalDataSource
import com.nroncari.movieplay.data.model.MovieDTO
import com.nroncari.movieplay.data.remotedatasource.MoviePagingSourceByGenre
import com.nroncari.movieplay.data.remotedatasource.MoviePagingSourceByKeyword
import com.nroncari.movieplay.data.remotedatasource.MoviePagingSourceRecommendations
import com.nroncari.movieplay.data.remotedatasource.MovieRemoteDataSource
import com.nroncari.movieplay.domain.model.MovieDataVideoDomain
import com.nroncari.movieplay.domain.model.MovieDetailDomain
import com.nroncari.movieplay.domain.model.MovieListItemDomain
import com.nroncari.movieplay.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
    private val moviePagingSourceByGenre: MoviePagingSourceByGenre,
    private val moviePagingSourceRecommendations: MoviePagingSourceRecommendations,
    private val moviePagingSourceByKeyword: MoviePagingSourceByKeyword,
) : MovieRepository {

    override fun getPagingMoviesByGenre(genre: Int): Flow<PagingData<MovieListItemDomain>> {
        moviePagingSourceByGenre.genre = genre
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { moviePagingSourceByGenre }
        ).flow
    }

    override fun getPagingMovieRecommendationsBy(movieId: Long): Flow<PagingData<MovieListItemDomain>> {
        moviePagingSourceRecommendations.movieId = movieId
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { moviePagingSourceByGenre }
        ).flow
    }

    override fun getPagingMovieByKeyword(keyword: String): Flow<PagingData<MovieListItemDomain>> {
        moviePagingSourceByKeyword.keyword = keyword
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { moviePagingSourceByKeyword }
        ).flow
    }

    override suspend fun getMovieDetailBy(movieId: Long): MovieDetailDomain {
        return remoteDataSource.getMovieDetailBy(movieId)
    }

    override suspend fun getMovieDataVideo(movieId: Long): List<MovieDataVideoDomain> {
        return remoteDataSource.getMovieDataVideoSource(movieId)
    }

    override fun insert(movie: MovieDTO, inFailureCase: () -> Unit, inSuccessCase: () -> Unit) =
        localDataSource.insert(movie, inFailureCase, inSuccessCase)

    override fun listAll() = localDataSource.listAll()

    override suspend fun returnById(movieId: Long) = localDataSource.returnById(movieId)

    override fun delete(movie: MovieDTO, inFailureCase: () -> Unit, inSuccessCase: () -> Unit) =
        localDataSource.delete(movie, inFailureCase, inSuccessCase)
}