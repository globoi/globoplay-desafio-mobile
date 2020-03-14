package br.com.nerdrapido.themoviedbapp.ui.moviedetail

import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.GetMovieUseCase
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractPresenterImpl
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
class MovieDetailPresenterImpl(
    private val getMovieUseCase: GetMovieUseCase,
    getLogInStateUseCase: GetLogInStateUseCase
) :
    AbstractPresenterImpl<MovieDetailView>(
        getLogInStateUseCase
    ), MovieDetailPresenter {

    override fun setMovie(movieListResultObject: MovieListResultObject) {
        runBlocking {
            launch(coroutineContext) {
                movieListResultObject.id?.let {
                    val movieResponse = getMovieUseCase.getMovieById(movieListResultObject.id)
                    view.movieLoaded(movieResponse)
                }
            }
        }
    }
}