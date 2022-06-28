package com.nroncari.movieplay.domain.repository

import com.nroncari.movieplay.domain.model.MovieListItemDomain

interface MovieRepository {

    suspend fun getMoviesByGenre(page: Int, genre: Int): List<MovieListItemDomain>
}