package com.nroncari.movieplay.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nroncari.movieplay.data.datasource.MovieDataSource
import com.nroncari.movieplay.data.datasource.MoviePagingSourceByGenre
import com.nroncari.movieplay.data.datasource.MoviePagingSourceRecommendations
import com.nroncari.movieplay.domain.model.MovieDataVideoDomain
import com.nroncari.movieplay.domain.model.MovieDetailDomain
import com.nroncari.movieplay.domain.model.MovieListItemDomain
import com.nroncari.movieplay.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val dataSource: MovieDataSource,
    private val moviePagingSourceByGenre: MoviePagingSourceByGenre,
    private val moviePagingSourceRecommendations: MoviePagingSourceRecommendations
) : MovieRepository {

    override fun getPagingMoviesByGenre(genre: Int): Flow<PagingData<MovieListItemDomain>> {
        moviePagingSourceByGenre.genre = genre
        return buildPage()
    }

    override fun getPagingMovieRecommendationsBy(movieId: Long): Flow<PagingData<MovieListItemDomain>> {
        moviePagingSourceRecommendations.movieId = movieId
        return buildPage()
    }

    override suspend fun getMovieDetailBy(movieId: Long): MovieDetailDomain {
        return dataSource.getMovieDetailBy(movieId)
    }

    override suspend fun getMovieDataVideo(movieId: Long): List<MovieDataVideoDomain> {
        return dataSource.getMovieDataVideoSource(movieId)
    }

    private fun buildPage() = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { moviePagingSourceByGenre }
    ).flow
}