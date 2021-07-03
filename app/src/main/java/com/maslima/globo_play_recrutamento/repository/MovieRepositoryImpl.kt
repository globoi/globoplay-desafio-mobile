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

    override suspend fun search(
        page: Int,
        query: String
    ): List<Movie> {
        val result = movieService.searchMovie(API_KEY, LANGUAGE_APP, page, query)
        return mapper.toDomainList(result.movies)
    }

    override suspend fun listMovies(page: Int): List<Movie> {
        return mapper.toDomainList(movieService.listMovies(API_KEY, LANGUAGE_APP, page).movies)
    }
}