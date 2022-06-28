package com.nroncari.movieplay.data.datasource

import com.nroncari.movieplay.domain.model.MovieListItemDomain

interface MovieDataSource {

    suspend fun getMoviesByGenre(page: Int, genre: Int) : List<MovieListItemDomain>
}