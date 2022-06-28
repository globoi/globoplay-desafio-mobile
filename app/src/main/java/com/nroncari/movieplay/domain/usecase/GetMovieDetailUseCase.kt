package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.domain.mapper.MovieDetailToPresentationMapper
import com.nroncari.movieplay.domain.repository.MovieRepository
import com.nroncari.movieplay.presentation.model.MovieDetailPresentation

class GetMovieDetailUseCase(
    private val repository: MovieRepository
) {

    private val mapper = MovieDetailToPresentationMapper()

    suspend operator fun invoke(movieId: Int): MovieDetailPresentation {
        return mapper.map(repository.getMovieDetailBy(movieId))
    }
}