package com.reisdeveloper.domain.usecases

import com.reisdeveloper.data.dataModel.MovieVideos
import com.reisdeveloper.data.repository.MovieRepository

class GetMovieVideosUseCase(
    private val userRepository: MovieRepository
) : AbstractUseCase<String, MovieVideos>() {

    override suspend fun execute(param: String): MovieVideos {
        return userRepository.getMovieVideos(param)
    }
}