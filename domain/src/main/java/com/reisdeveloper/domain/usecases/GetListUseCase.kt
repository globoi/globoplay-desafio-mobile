package com.reisdeveloper.domain.usecases

import com.reisdeveloper.data.dataModel.FavoriteMovies
import com.reisdeveloper.data.repository.ListsRepository

class GetListUseCase(
    private val userRepository: ListsRepository
) : AbstractUseCase<String, FavoriteMovies>() {

    override suspend fun execute(param: String): FavoriteMovies {
        return userRepository.getMyLists(param)
    }
}