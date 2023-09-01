package com.mazer.globoplayapp.domain.use_cases

import com.mazer.globoplayapp.data.repos.MovieRepository
import com.mazer.globoplayapp.domain.entities.Movie

/**
 * Essa classe agrupa os UseCases de carregar os filmes "Popular", "Top Rated" e "Upcoming"
 *
 */
class GetMovieListUseCase(private val movieRepository: MovieRepository) {

    suspend fun getPopularMovies(): List<Movie> {
        val popularMovies = movieRepository.getPopularMovies()
        val genreList = movieRepository.getGenreList()

        popularMovies.forEach { movies ->
            val genre = genreList.filter { it.id == movies.genreIds[0] }[0]
            movies.genre = genre.name
        }

        return popularMovies
    }

    suspend fun getTopRatedMovies(): List<Movie> {
        val topRatedMovies = movieRepository.getTopRatedMovies()
        val genreList = movieRepository.getGenreList()

        topRatedMovies.forEach { movies ->
            val genre = genreList.filter { it.id == movies.genreIds[0] }[0]
            movies.genre = genre.name
        }

        return topRatedMovies
    }

    suspend fun getUpcomingMovies(): List<Movie> {
        val upcomingMovies = movieRepository.getUpcomingMovies()
        val genreList = movieRepository.getGenreList()

        upcomingMovies.forEach { movies ->
            val genre = genreList.filter { it.id == movies.genreIds[0] }[0]
            movies.genre = genre.name
        }

        return upcomingMovies
    }
}