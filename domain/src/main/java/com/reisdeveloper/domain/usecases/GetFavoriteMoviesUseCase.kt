package com.reisdeveloper.domain.usecases

import com.reisdeveloper.data.dataModel.MovieList
import com.reisdeveloper.data.repository.MovieRepository

class GetFavoriteMoviesUseCase(
    private val userRepository: MovieRepository
) : AbstractUseCase<Unit, MovieList>() {

    override suspend fun execute(param: Unit): MovieList {
        return userRepository.getFavoriteMovies()
    }
}