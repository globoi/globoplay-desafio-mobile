package br.com.favorites.data.repository.datasource

import br.com.common.data.dto.ResultMoviesDto

interface FavoritiesRemoteDataSource {
    suspend fun getFavorities(authorization: String, page: Int,account: Int) : Result<ResultMoviesDto>
}