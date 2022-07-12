package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.domain.repository.MovieLocalRepository

class GetMovieDatabaseById(
    private val repository: MovieLocalRepository
) {
    suspend operator fun invoke(movieId: Long) = repository.returnById(movieId)
}