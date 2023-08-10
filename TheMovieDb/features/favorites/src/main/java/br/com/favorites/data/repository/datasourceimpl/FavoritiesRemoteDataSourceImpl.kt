package br.com.favorites.data.repository.datasourceimpl

import br.com.common.data.dto.ResultMoviesDto
import br.com.favorites.data.remote.FavoriteApiService
import javax.inject.Inject



class FavoritiesRemoteDataSourceImpl @Inject constructor(
    private val service: FavoriteApiService
) : br.com.favorites.data.repository.datasource.FavoritiesRemoteDataSource {
    override suspend fun getFavorities(authorization : String, page: Int, account: Int):
            Result<ResultMoviesDto> = service.getMoviesFavorities( authorization = authorization,
        account = account,
        page = page)

}