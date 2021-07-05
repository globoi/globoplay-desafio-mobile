package com.maslima.globo_play_recrutamento.repository

import com.maslima.globo_play_recrutamento.domain.model.Movie
import com.maslima.globo_play_recrutamento.network.MovieService
import com.maslima.globo_play_recrutamento.network.model.MovieDtoMapper
import com.maslima.globo_play_recrutamento.utils.API_KEY
import com.maslima.globo_play_recrutamento.utils.LANGUAGE_APP

class MovieRepositoryImpl(
    private val movieService: MovieService,
    private val mapper: MovieDtoMapper
) : MovieRepository {

    override suspend fun listMovies(page: Int, query: String?, categoryId: Int?): List<Movie> {
        query?.let { text ->
            if (text.isNotBlank()) {
                return searchWithDescription(page, text)
            }
        }
        categoryId?.let { genreId ->
            if (categoryId != 0) {
                return searchByCategory(page, genreId)
            }
        }
        return mapper.toDomainList(movieService.listMovies(API_KEY, LANGUAGE_APP, page).movies)
    }

    private suspend fun searchByCategory(
        page: Int,
        genreId: Int
    ): List<Movie> {
        return mapper.toDomainList(
            movieService.listMoviesByCategory(
                API_KEY,
                LANGUAGE_APP,
                page,
                genreId
            ).movies
        )
    }

    private suspend fun searchWithDescription(
        page: Int,
        query: String
    ): List<Movie> {
        return mapper.toDomainList(
            movieService.searchMovie(
                API_KEY,
                LANGUAGE_APP,
                page,
                query
            ).movies
        )
    }

    override suspend fun getMovie(movieId: Int): Movie {
        return mapper.mapToDomainModel(movieService.getMovie(movieId, API_KEY, LANGUAGE_APP))
    }
}