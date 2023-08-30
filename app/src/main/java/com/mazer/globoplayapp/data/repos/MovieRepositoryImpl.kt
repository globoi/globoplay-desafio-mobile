package com.mazer.globoplayapp.data.repos

import com.mazer.globoplayapp.data.datasource.MovieDataSource
import com.mazer.globoplayapp.domain.entities.Movie

class MovieRepositoryImpl(private val movieDataSource: MovieDataSource) : MovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        return movieDataSource.getPopularMoviesFromRemote()
    }

    override suspend fun getTopRatedMovies(): List<Movie> {
        return movieDataSource.getTopRatedMoviesFromRemote()
    }

    override suspend fun getUpcomingMovies(): List<Movie> {
        return movieDataSource.getUpcomingFromRemote()
    }

}