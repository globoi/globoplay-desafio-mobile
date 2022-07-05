package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.data.datasource.Genre.HORROR
import com.nroncari.movieplay.domain.repository.MovieRepository

class GetHorrorMoviesUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke() = repository.getPagingMoviesByGenre(HORROR)
}