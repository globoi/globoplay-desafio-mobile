package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.domain.repository.MovieLocalRepository
import com.nroncari.movieplay.domain.repository.MovieRemoteRepository

class ListAllMovieDatabaseUseCase(
    private val repository: MovieLocalRepository
) {
    operator fun invoke() = repository.listAll()
}