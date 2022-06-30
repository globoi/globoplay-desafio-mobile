package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.data.datasource.Genre.ACTION
import com.nroncari.movieplay.domain.repository.MovieRepository

class GetActionMoviesUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke() = repository.getPagingMovies(ACTION)
}