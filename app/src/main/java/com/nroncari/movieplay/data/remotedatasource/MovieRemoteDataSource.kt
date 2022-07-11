package com.nroncari.movieplay.data.remotedatasource

import com.nroncari.movieplay.domain.model.MovieDataVideoDomain
import com.nroncari.movieplay.domain.model.MovieDetailDomain
import com.nroncari.movieplay.domain.model.MovieListItemDomain

interface MovieRemoteDataSource {

    suspend fun getMoviesByGenre(page: Int, genre: Int) : List<MovieListItemDomain>

    suspend fun getMovieDetailBy(movieId: Long) : MovieDetailDomain

    suspend fun getMovieRecommendationsBy(movieId: Long) : List<MovieListItemDomain>

    suspend fun getMovieDataVideoSource(movieId: Long) : List<MovieDataVideoDomain>

    suspend fun getMoviesByKeyword(keyword: String) : List<MovieListItemDomain>
}