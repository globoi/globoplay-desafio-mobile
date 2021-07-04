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
                return mapper.toDomainList(
                    movieService.searchMovie(
                        API_KEY,
                        LANGUAGE_APP,
                        page,
                        query
                    ).movies
                )
            }
        }
        categoryId?.let { genreId ->
            if (categoryId != 0) {
                return mapper.toDomainList(
                    movieService.listMoviesByCategory(
                        API_KEY,
                        LANGUAGE_APP,
                        page,
                        genreId
                    ).movies
                )
            }
        }
        return mapper.toDomainList(movieService.listMovies(API_KEY, LANGUAGE_APP, page).movies)
    }
}