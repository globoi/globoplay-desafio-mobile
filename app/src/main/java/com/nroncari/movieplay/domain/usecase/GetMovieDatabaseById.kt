package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.domain.repository.MovieRepository

class GetMovieDatabaseById(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Long) = repository.returnById(movieId)
}