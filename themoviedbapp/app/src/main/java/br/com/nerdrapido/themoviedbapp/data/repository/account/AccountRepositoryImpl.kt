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
import br.com.nerdrapido.themoviedbapp.data.repository.abstracts.AbstractMovieDbApiRepos
import br.com.nerdrapido.themoviedbapp.data.repository.session.SessionRepository
import retrofit2.Retrofit

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 */
class AccountRepositoryImpl(val sessionRepository: SessionRepository, retrofit: Retrofit) :
    AbstractMovieDbApiRepos(retrofit),
    AccountRepository {

    private val authService: AccountService = retrofit.create(AccountService::class.java)

    override suspend fun getAccount(accountRequest: AccountRequest): AccountResponse {
        return authService.getAccount(accountRequest.sessionId)
    }

    override suspend fun getFavoriteMovies(favoriteMoviesRequest: FavoriteMoviesRequest): FavoriteMoviesResponse {
        return authService.getFavoriteMovies(
            favoriteMoviesRequest.accountId,
            favoriteMoviesRequest.language,
            favoriteMoviesRequest.sessionId,
            favoriteMoviesRequest.sortBy,
            favoriteMoviesRequest.page
        )
    }

    override suspend fun getWatchlistMovies(watchlistMoviesRequest: WatchlistMoviesRequest): WatchlistMoviesResponse {
        return authService.getWatchlistMovies(
            watchlistMoviesRequest.accountId,
            watchlistMoviesRequest.language,
            watchlistMoviesRequest.sessionId,
            watchlistMoviesRequest.sortBy,
            watchlistMoviesRequest.page
        )
    }

    override suspend fun markMovieToFavorite(postFavoriteRequest: PostFavoriteRequest): PostFavoriteResponse {
        return authService.markMovieToFavorite(
            sessionRepository.getAccountId().toString(),
            sessionRepository.getSessionId(),
            PostFavoriteRequest(
                postFavoriteRequest.mediaType,
                postFavoriteRequest.mediaId,
                postFavoriteRequest.favorite
            )

        )
    }

    override suspend fun addMovieToWatchlist(postWatchlistRequest: PostWatchlistRequest): PostWatchlistResponse {
        return authService.saveMovieToWatchlist(
            sessionRepository.getAccountId().toString(),
            sessionRepository.getSessionId(),
            PostWatchlistRequest(
                postWatchlistRequest.mediaType,
                postWatchlistRequest.mediaId,
                postWatchlistRequest.watchlist
            )
        )
    }
}