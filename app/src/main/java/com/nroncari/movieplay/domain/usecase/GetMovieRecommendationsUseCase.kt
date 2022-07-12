package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.domain.repository.MovieRemoteRepository

class GetMovieRecommendationsUseCase(
    private val repository: MovieRemoteRepository
) {
    operator fun invoke(movieId: Long) = repository.getPagingMovieRecommendationsBy(movieId)
}