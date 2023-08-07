package com.reisdeveloper.domain.usecases

import com.reisdeveloper.data.dataModel.MovieList
import com.reisdeveloper.data.repository.MovieRepository

class GetSimilarMoviesUseCase(
    private val userRepository: MovieRepository
) : AbstractUseCase<String, MovieList>() {

    override suspend fun execute(param: String): MovieList {
        return userRepository.getSimilarMovies(movieId = param)
    }
}