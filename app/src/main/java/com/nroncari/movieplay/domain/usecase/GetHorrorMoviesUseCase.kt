package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.data.remotedatasource.Genre.HORROR
import com.nroncari.movieplay.domain.repository.MovieRemoteRepository

class GetHorrorMoviesUseCase(
    private val repository: MovieRemoteRepository
) {
    operator fun invoke() = repository.getPagingMoviesByGenre(HORROR)
}