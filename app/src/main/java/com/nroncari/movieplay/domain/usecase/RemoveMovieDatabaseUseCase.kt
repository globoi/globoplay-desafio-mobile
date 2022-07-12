package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.domain.mapper.MovieDetailToDTOMapper
import com.nroncari.movieplay.domain.repository.MovieLocalRepository
import com.nroncari.movieplay.presentation.model.MovieDetailPresentation

class RemoveMovieDatabaseUseCase(
    private val repository: MovieLocalRepository
) {
    private val mapper = MovieDetailToDTOMapper()

    operator fun invoke(
        movie: MovieDetailPresentation,
        inFailureCase: () -> Unit,
        inSuccessCase: () -> Unit
    ) {
        repository.delete(
            mapper.map(movie),
            inFailureCase,
            inSuccessCase
        )
    }
}