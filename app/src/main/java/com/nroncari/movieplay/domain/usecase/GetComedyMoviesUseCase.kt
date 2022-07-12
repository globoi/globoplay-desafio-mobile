package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.data.remotedatasource.Genre.COMEDY
import com.nroncari.movieplay.domain.repository.MovieRemoteRepository

class GetComedyMoviesUseCase(
    private val repository: MovieRemoteRepository
) {
    operator fun invoke() = repository.getPagingMoviesByGenre(COMEDY)
}