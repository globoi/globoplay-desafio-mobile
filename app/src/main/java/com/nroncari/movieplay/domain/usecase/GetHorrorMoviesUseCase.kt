package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.data.remotedatasource.Genre.HORROR
import com.nroncari.movieplay.domain.repository.MovieRepository

class GetHorrorMoviesUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke() = repository.getPagingMoviesByGenre(HORROR)
}