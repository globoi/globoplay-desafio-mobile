package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.data.remotedatasource.Genre.DRAMA
import com.nroncari.movieplay.domain.repository.MovieRemoteRepository

class GetDramaMoviesUseCase(
    private val repository: MovieRemoteRepository
) {
    operator fun invoke() = repository.getPagingMoviesByGenre(DRAMA)
}