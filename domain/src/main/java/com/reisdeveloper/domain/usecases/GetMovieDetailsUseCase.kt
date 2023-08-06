package com.reisdeveloper.domain.usecases

import com.reisdeveloper.data.dataModel.MovieDetails
import com.reisdeveloper.data.repository.MovieRepository

class GetMovieDetailsUseCase(
    private val userRepository: MovieRepository
) : AbstractUseCase<String, MovieDetails>() {

    override suspend fun execute(param: String): MovieDetails {
        return userRepository.getMovieDetails(null, param)
    }
}