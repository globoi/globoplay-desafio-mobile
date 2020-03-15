package br.com.nerdrapido.themoviedbapp.data.repository.account

import br.com.nerdrapido.themoviedbapp.data.model.account.AccountRequest
import br.com.nerdrapido.themoviedbapp.data.model.account.AccountResponse
import br.com.nerdrapido.themoviedbapp.data.model.favoritemovies.FavoriteMoviesRequest
import br.com.nerdrapido.themoviedbapp.data.model.favoritemovies.FavoriteMoviesResponse

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 */
interface AccountRepository {

    suspend fun getAccount(accountRequest: AccountRequest): AccountResponse

    suspend fun getFavoriteMovies(favoriteMoviesRequest: FavoriteMoviesRequest): FavoriteMoviesResponse

}