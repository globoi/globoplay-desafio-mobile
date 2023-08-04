package com.reisdeveloper.domain.usecases

import com.reisdeveloper.data.dataModel.MovieVideos
import com.reisdeveloper.data.repository.ListsRepository

class GetMovieVideosUseCase(
    private val userRepository: ListsRepository
) : AbstractUseCase<String, MovieVideos>() {

    override suspend fun execute(param: String): MovieVideos {
        return userRepository.getMovieVideos(param)
    }
}