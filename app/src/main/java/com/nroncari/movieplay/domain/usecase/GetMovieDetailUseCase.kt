package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.domain.mapper.MovieDetailToPresentationMapper
import com.nroncari.movieplay.domain.repository.MovieRemoteRepository
import com.nroncari.movieplay.presentation.model.MovieDetailPresentation

class GetMovieDetailUseCase(
    private val repository: MovieRemoteRepository
) {

    private val mapper = MovieDetailToPresentationMapper()

    suspend operator fun invoke(movieId: Long): MovieDetailPresentation {
        return mapper.map(repository.getMovieDetailBy(movieId))
    }
}