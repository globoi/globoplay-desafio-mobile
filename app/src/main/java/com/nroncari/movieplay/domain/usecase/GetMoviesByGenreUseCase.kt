package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.domain.mapper.MovieToPresentationMapper
import com.nroncari.movieplay.domain.repository.MovieRepository
import com.nroncari.movieplay.presentation.model.MovieListItemPresentation

class GetMoviesByGenreUseCase(
    private val repository: MovieRepository
) {

    private val mapper = MovieToPresentationMapper()

    suspend operator fun invoke(page: Int, genre: Int): List<MovieListItemPresentation> {
        return repository.getMoviesByGenre(page, genre).map { movieDomain ->
            mapper.map(movieDomain)
        }
    }
}