package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.domain.repository.MovieRepository

class ListAllMovieDatabaseUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke() = repository.listAll()
}