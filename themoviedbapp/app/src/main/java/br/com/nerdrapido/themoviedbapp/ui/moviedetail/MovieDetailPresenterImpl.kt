package br.com.nerdrapido.themoviedbapp.ui.moviedetail

import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.LogoutUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.MovieUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.WatchlistMoviesUseCase
import br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation.NavigationPresenterImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
class MovieDetailPresenterImpl(
    logoutUseCase: LogoutUseCase,
    private val accountUseCase: WatchlistMoviesUseCase,
    private val movieUseCase: MovieUseCase,
    getLogInStateUseCase: GetLogInStateUseCase
) :
    NavigationPresenterImpl<MovieDetailView>(
        logoutUseCase,
        getLogInStateUseCase
    ), MovieDetailPresenter {

    private var isMovieInWatchlist: Boolean? = null

    private var movieListResultObject: MovieListResultObject? = null

    override fun setMovie(movieListResultObject: MovieListResultObject) {
        this.movieListResultObject = movieListResultObject
        initMovieInfo(movieListResultObject)
    }

    override suspend fun loadRelatedMoviePage(page: Int): List<MovieListResultObject> {
        return movieListResultObject?.id?.let {
            val movieResponse = movieUseCase.getMovieRecommendationByMovieId(it, 1)
            movieResponse.results
        } ?: emptyList()
    }

    override fun myListButtonClicked() {
        // Invert the isMovieInWatchlist state
        var isMovieInWatchlistLocal: Boolean? = isMovieInWatchlist ?: return
        isMovieInWatchlistLocal ?: return
        isMovieInWatchlistLocal = !isMovieInWatchlistLocal
        isMovieInWatchlist = isMovieInWatchlistLocal
        GlobalScope.launch {
            movieListResultObject?.let {
                accountUseCase.addMovieToWatchlist(it, isMovieInWatchlistLocal)
                view.setMovieListState(isMovieInWatchlistLocal)
            }
        }
    }

    private fun initMovieInfo(movieListResultObject: MovieListResultObject) {
        GlobalScope.launch {
            movieListResultObject.id?.let {
                val movieStateResponse = movieUseCase.getMovieAccountState(it)
                isMovieInWatchlist = movieStateResponse.watchlist
                view.setMovieListState(isMovieInWatchlist)
            }
        }
        GlobalScope.launch {
            movieListResultObject.id?.let {
                val movieResponse = movieUseCase.getMovieById(it)
                view.movieInfoLoaded(movieResponse)
            }
        }
        GlobalScope.launch {
            movieListResultObject.id?.let {
                val movieUrl = movieUseCase.getMovieVideoUrl(it)
                movieUrl?.let { movieUrlLocal -> view.movieVideoLoaded(movieUrlLocal) }
            }
        }
    }
}