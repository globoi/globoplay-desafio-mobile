package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.data.remotedatasource.Genre.ANIMATION
import com.nroncari.movieplay.domain.repository.MovieRemoteRepository

class GetAnimationMoviesUseCase(
    private val repository: MovieRemoteRepository
) {
    operator fun invoke() = repository.getPagingMoviesByGenre(ANIMATION)
}