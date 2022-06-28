package com.nroncari.movieplay.data.repository

import com.nroncari.movieplay.data.datasource.MovieDataSource
import com.nroncari.movieplay.domain.model.MovieListItemDomain
import com.nroncari.movieplay.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val dataSource: MovieDataSource
) : MovieRepository {

    override suspend fun getMoviesByGenre(page: Int, genre: Int): List<MovieListItemDomain> {
        return dataSource.getMoviesByGenre(page, genre)
    }
}