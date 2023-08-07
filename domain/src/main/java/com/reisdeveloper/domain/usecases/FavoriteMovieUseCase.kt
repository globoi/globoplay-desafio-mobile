package com.reisdeveloper.domain.usecases

import com.reisdeveloper.data.dataModel.Favorite
import com.reisdeveloper.data.repository.MovieRepository

class FavoriteMovieUseCase(
private val userRepository: MovieRepository
) : AbstractUseCase<Favorite, Unit>() {

    override suspend fun execute(param: Favorite): Unit {
        return userRepository.favoriteMovie(param)
    }
}