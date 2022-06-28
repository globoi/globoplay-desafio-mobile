package com.nroncari.movieplay.data.datasource

import com.nroncari.movieplay.data.mapper.MovieToDomainMapper
import com.nroncari.movieplay.data.service.MovieService
import com.nroncari.movieplay.domain.model.MovieListItemDomain

class MovieDataSourceImpl(
    private val service: MovieService
) : MovieDataSource {

    private val mapper = MovieToDomainMapper()

    override suspend fun getMoviesByGenre(page: Int, genre: Int): List<MovieListItemDomain> {
        return service.getMoviesByGenre(1, 28).results.map { movieResponse ->
            mapper.map(movieResponse)
        }
    }
}