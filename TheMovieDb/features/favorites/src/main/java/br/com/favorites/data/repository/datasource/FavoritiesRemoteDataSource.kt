package br.com.favorites.data.repository.datasource

import br.com.common.data.dto.ResultMoviesDto
import br.com.favorites.data.remote.dto.AddOrRemoveFavoriteDto
import br.com.favorites.data.remote.dto.ResultAddFavoriteDto

interface FavoritiesRemoteDataSource {
    suspend fun getFavorities(authorization: String, page: Int,account: Int) : Result<ResultMoviesDto>
    suspend fun addFavorite(account: Int, movie: AddOrRemoveFavoriteDto) : Result<ResultAddFavoriteDto>
}