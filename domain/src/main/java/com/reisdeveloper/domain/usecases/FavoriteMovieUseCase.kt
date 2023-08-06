package com.reisdeveloper.domain.usecases

import com.reisdeveloper.data.dataModel.Favorite
import com.reisdeveloper.data.repository.MovieRepository

class FavoriteMovieUseCase(
private val userRepository: MovieRepository
) : AbstractUseCase<Pair<String, Favorite>, Unit>() {

    override suspend fun execute(param: Pair<String, Favorite>): Unit {
        return userRepository.favoriteMovie(param.first, param.second)
    }
}