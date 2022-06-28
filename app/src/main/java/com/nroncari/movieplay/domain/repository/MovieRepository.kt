package com.nroncari.movieplay.domain.repository

import com.nroncari.movieplay.domain.model.MovieDetailDomain
import com.nroncari.movieplay.domain.model.MovieListItemDomain

interface MovieRepository {

    suspend fun getMoviesByGenre(page: Int, genre: Int): List<MovieListItemDomain>

    suspend fun getMovieDetailBy(movieId: Int): MovieDetailDomain
}