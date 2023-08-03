package com.reisdeveloper.data.repository

import com.reisdeveloper.data.api.MovieApi
import com.reisdeveloper.data.dataModel.FavoriteMovies

interface ListsRepository {
    suspend fun getMyLists(accountId: String): FavoriteMovies
    suspend fun getFavoriteMovies(accountId: String): FavoriteMovies
}

class ListsRepositoryImpl(
    private val movieApi: MovieApi
): ListsRepository {

    override suspend fun getMyLists(accountId: String): FavoriteMovies {
        return movieApi.getMyLists(accountId)
    }

    override suspend fun getFavoriteMovies(accountId: String): FavoriteMovies {
        return movieApi.getFavoriteMovies(accountId)
    }

}