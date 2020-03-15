package br.com.nerdrapido.themoviedbapp.ui.moviedetail

import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.MovieUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.LogoutUseCase
import br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation.NavigationPresenterImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
class MovieDetailPresenterImpl(
    logoutUseCase: LogoutUseCase,
    private val movieUseCase: MovieUseCase,
    getLogInStateUseCase: GetLogInStateUseCase
) :
    NavigationPresenterImpl<MovieDetailView>(
        logoutUseCase,
        getLogInStateUseCase
    ), MovieDetailPresenter {

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

    private fun initMovieInfo(movieListResultObject: MovieListResultObject) {
        GlobalScope.launch {
            movieListResultObject.id?.let {
                val movieResponse = movieUseCase.getMovieById(it)
                view.movieInfoLoaded(movieResponse)
            }
        }
    }
}