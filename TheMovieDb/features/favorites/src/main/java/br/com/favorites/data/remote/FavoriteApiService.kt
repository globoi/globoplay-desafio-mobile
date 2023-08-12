package br.com.favorites.data.remote

import br.com.common.data.dto.ResultMoviesDto
import br.com.favorites.data.remote.dto.AddOrRemoveFavoriteDto
import br.com.favorites.data.remote.dto.ResultAddFavoriteDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FavoriteApiService {
    @GET("/3/account/{account}/favorite/movies")
    suspend fun getMoviesFavorities(@Header("Authorization") authorization: String,
                                    @Path("account") account: Int,
                                    @Query("page") page: Int) : Result<ResultMoviesDto>

    @POST("/3/account/{account}/favorite")
    suspend fun addMovieFavorite(@Header("Authorization") authorization: String,
                                    @Path("account") account: Int,
                                  @Body movie: AddOrRemoveFavoriteDto)
    : Result<ResultAddFavoriteDto>

}