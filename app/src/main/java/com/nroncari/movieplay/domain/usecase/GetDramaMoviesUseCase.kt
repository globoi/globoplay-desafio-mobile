package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.data.datasource.Genre.DRAMA
import com.nroncari.movieplay.domain.repository.MovieRepository

class GetDramaMoviesUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke() = repository.getPagingMovies(DRAMA)
}