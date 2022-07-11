package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.data.remotedatasource.Genre.ANIMATION
import com.nroncari.movieplay.domain.repository.MovieRepository

class GetAnimationMoviesUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke() = repository.getPagingMoviesByGenre(ANIMATION)
}