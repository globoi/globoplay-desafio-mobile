package com.mazer.globoplayapp.domain.use_cases

import com.mazer.globoplayapp.data.repos.MovieRepository
import com.mazer.globoplayapp.domain.entities.Movie


class GetMovieListUseCase(private val movieRepository: MovieRepository) {

    suspend fun getPopularMovies(): List<Movie> {
        return movieRepository.getPopularMovies()
    }

    suspend fun getTopRatedMovies(): List<Movie> {
        return movieRepository.getTopRatedMovies()
    }

    suspend fun getUpcomingMovies(): List<Movie> {
        return movieRepository.getUpcomingMovies()
    }
}