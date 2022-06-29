package com.nroncari.movieplay.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nroncari.movieplay.data.datasource.MovieDataSource
import com.nroncari.movieplay.data.datasource.MoviePagingSource
import com.nroncari.movieplay.data.model.MovieListItemResponse
import com.nroncari.movieplay.domain.model.MovieDetailDomain
import com.nroncari.movieplay.domain.model.MovieListItemDomain
import com.nroncari.movieplay.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val dataSource: MovieDataSource,
    private val moviePagingSource: MoviePagingSource
) : MovieRepository {

    override suspend fun getMoviesByGenre(page: Int, genre: Int): List<MovieListItemDomain> {
        return dataSource.getMoviesByGenre(page, genre)
    }

    override fun getPagingMovies(): Flow<PagingData<MovieListItemDomain>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { moviePagingSource }
        ).flow
    }

    override suspend fun getMovieDetailBy(movieId: Int): MovieDetailDomain {
        return dataSource.getMovieDetailBy(movieId)
    }
}