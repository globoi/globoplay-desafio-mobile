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
        val movieId = movieListResultObject?.id ?: return emptyList()
        var movieList: List<MovieListResultObject> = emptyList()
        onResponseWrapper(
            movieUseCase.getMovieRecommendationByMovieId(movieId, 1)
        ) { responseObject -> movieList = responseObject.results ?: emptyList() }
        return movieList
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
        val movieId = movieListResultObject.id ?: return
        GlobalScope.launch {
            onResponseWrapper(movieUseCase.getMovieAccountState(movieId)) {
                isMovieInWatchlist = it.watchlist
                view.setMovieListState(isMovieInWatchlist)
            }
        }
        GlobalScope.launch {
            onResponseWrapper(movieUseCase.getMovieById(movieId)) {
                view.movieInfoLoaded(it)
            }
        }
        GlobalScope.launch {
            val movieUrl = movieUseCase.getMovieVideoUrl(movieId)
            movieUrl?.let { movieUrlLocal -> view.movieVideoLoaded(movieUrlLocal) }
        }
    }
}