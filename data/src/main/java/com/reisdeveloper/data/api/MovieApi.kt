package com.reisdeveloper.data.api

import com.reisdeveloper.data.dataModel.FavoriteMovies
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    //Todo alterar response
    @GET("3/account/{account_id}/lists")
    suspend fun getMyLists(
        @Path("account_id") accountId: String
    ): FavoriteMovies

    @GET("3/account/{account_id}/favorite/movies")
    suspend fun getFavoriteMovies(
        @Path("account_id") accountId: String
    ): FavoriteMovies
}