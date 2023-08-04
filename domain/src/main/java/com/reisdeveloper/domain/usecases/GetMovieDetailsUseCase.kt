package com.reisdeveloper.domain.usecases

import com.reisdeveloper.data.dataModel.MovieDetails
import com.reisdeveloper.data.repository.ListsRepository

class GetMovieDetailsUseCase(
    private val userRepository: ListsRepository
) : AbstractUseCase<String, MovieDetails>() {

    override suspend fun execute(param: String): MovieDetails {
        return userRepository.getMovieDetails(param)
    }
}