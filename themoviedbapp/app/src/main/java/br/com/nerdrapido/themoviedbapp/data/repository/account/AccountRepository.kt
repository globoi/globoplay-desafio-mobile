package br.com.nerdrapido.themoviedbapp.data.repository.account

import br.com.nerdrapido.themoviedbapp.data.model.account.AccountRequest
import br.com.nerdrapido.themoviedbapp.data.model.account.AccountResponse
import br.com.nerdrapido.themoviedbapp.data.model.addfavorite.PostFavoriteRequest
import br.com.nerdrapido.themoviedbapp.data.model.addfavorite.PostFavoriteResponse
import br.com.nerdrapido.themoviedbapp.data.model.addwatchlist.PostWatchlistRequest
import br.com.nerdrapido.themoviedbapp.data.model.addwatchlist.PostWatchlistResponse
import br.com.nerdrapido.themoviedbapp.data.model.favoritemovies.FavoriteMoviesRequest
import br.com.nerdrapido.themoviedbapp.data.model.favoritemovies.FavoriteMoviesResponse
import br.com.nerdrapido.themoviedbapp.data.model.watchlistmovies.WatchlistMoviesRequest
import br.com.nerdrapido.themoviedbapp.data.model.watchlistmovies.WatchlistMoviesResponse

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 */
interface AccountRepository {

    suspend fun getAccount(accountRequest: AccountRequest): AccountResponse

    suspend fun getFavoriteMovies(favoriteMoviesRequest: FavoriteMoviesRequest): FavoriteMoviesResponse

    suspend fun getWatchlistMovies(watchlistMoviesRequest: WatchlistMoviesRequest): WatchlistMoviesResponse

    suspend fun markMovieToFavorite(postFavoriteRequest: PostFavoriteRequest): PostFavoriteResponse

    suspend fun addMovieToWatchlist(postWatchlistRequest: PostWatchlistRequest): PostWatchlistResponse

}