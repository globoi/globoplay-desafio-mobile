package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.data.datasource.Genre.ANIMATION
import com.nroncari.movieplay.domain.repository.MovieRepository

class GetAnimationMoviesUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke() = repository.getPagingMovies(ANIMATION)
}