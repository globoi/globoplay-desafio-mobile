package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.domain.repository.MovieRepository

class GetMovieRecommendationsUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke(movieId: Long) = repository.getPagingMovieRecommendationsBy(movieId)
}