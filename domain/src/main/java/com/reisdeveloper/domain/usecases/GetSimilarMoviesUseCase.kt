package com.reisdeveloper.domain.usecases

import com.reisdeveloper.data.dataModel.MovieList
import com.reisdeveloper.data.repository.ListsRepository

class GetSimilarMoviesUseCase(
    private val userRepository: ListsRepository
) : AbstractUseCase<String, MovieList>() {

    override suspend fun execute(param: String): MovieList {
        return userRepository.getSimilarMovies(param)
    }
}