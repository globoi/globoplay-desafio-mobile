package com.nroncari.movieplay.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nroncari.movieplay.data.datasource.Genre.ACTION
import com.nroncari.movieplay.data.datasource.Genre.HORROR
import com.nroncari.movieplay.data.datasource.MovieDataSource
import com.nroncari.movieplay.data.datasource.MoviePagingSource
import com.nroncari.movieplay.domain.model.MovieDetailDomain
import com.nroncari.movieplay.domain.model.MovieListItemDomain
import com.nroncari.movieplay.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val dataSource: MovieDataSource,
    private val moviePagingSource: MoviePagingSource
) : MovieRepository {

    override fun getPagingMovies(genre: Int): Flow<PagingData<MovieListItemDomain>> {
        moviePagingSource.genre = genre
        return buildPage()
    }

    private fun buildPage() = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { moviePagingSource }
    ).flow

    override suspend fun getMovieDetailBy(movieId: Long): MovieDetailDomain {
        return dataSource.getMovieDetailBy(movieId)
    }
}