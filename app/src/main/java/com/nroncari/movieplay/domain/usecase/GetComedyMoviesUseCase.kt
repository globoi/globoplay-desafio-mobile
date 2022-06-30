package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.data.datasource.Genre.COMEDY
import com.nroncari.movieplay.domain.repository.MovieRepository

class GetComedyMoviesUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke() = repository.getPagingMovies(COMEDY)
}