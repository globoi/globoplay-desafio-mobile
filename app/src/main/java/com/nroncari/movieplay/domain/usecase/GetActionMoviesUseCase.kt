package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.data.remotedatasource.Genre.ACTION
import com.nroncari.movieplay.domain.repository.MovieRemoteRepository

class GetActionMoviesUseCase(
    private val repository: MovieRemoteRepository
) {
    operator fun invoke() = repository.getPagingMoviesByGenre(ACTION)
}