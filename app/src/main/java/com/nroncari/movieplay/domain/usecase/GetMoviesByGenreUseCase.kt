package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.domain.mapper.MovieToPresentationMapper
import com.nroncari.movieplay.domain.repository.MovieRepository
import com.nroncari.movieplay.presentation.model.MovieListItemPresentation

class GetMoviesByGenreUseCase(
    private val repository: MovieRepository
) {

    private val mapper = MovieToPresentationMapper()

    fun execute(genre: Int) =
         repository.getPagingMovies()
}