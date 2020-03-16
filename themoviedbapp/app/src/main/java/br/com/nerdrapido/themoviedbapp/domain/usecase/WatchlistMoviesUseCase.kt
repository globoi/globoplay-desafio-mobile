package br.com.nerdrapido.themoviedbapp.domain.usecase

import br.com.nerdrapido.themoviedbapp.data.model.MediaTypes
import br.com.nerdrapido.themoviedbapp.data.model.addwatchlist.PostWatchlistRequest
import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.data.model.watchlistmovies.WatchlistMoviesRequest
import br.com.nerdrapido.themoviedbapp.data.repository.account.AccountRepository
import br.com.nerdrapido.themoviedbapp.data.repository.session.SessionRepository

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 */
class WatchlistMoviesUseCase(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val sessionRepository: SessionRepository,
    private val accountRepository: AccountRepository
) {

    suspend fun getWatchlistMovies(page: Int): List<MovieListResultObject> {
        return accountRepository.getWatchlistMovies(
            WatchlistMoviesRequest(
                sessionRepository.getAccountId().toString(),
                sessionRepository.getSessionId() ?: "",
                getLanguageUseCase.getLanguage(),
                "created_at.desc",
                page
            )
        ).results ?: emptyList()
    }

    /**
     * Save a [MovieListResultObject] to the watchlist.
     *
     * TODO: make null treatment better
     */
    suspend fun addMovieToWatchlist(movieListResultObject: MovieListResultObject, addMovie: Boolean): Boolean {
        val response = movieListResultObject.id?.let {
            accountRepository.addMovieToWatchlist(
                PostWatchlistRequest(
                    MediaTypes.MOVIE.description,
                    it,
                    addMovie
                )
            )
        }
        return response != null
    }
}