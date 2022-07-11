package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.data.remotedatasource.Genre.ACTION
import com.nroncari.movieplay.domain.repository.MovieRepository

class GetActionMoviesUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke() = repository.getPagingMoviesByGenre(ACTION)
}