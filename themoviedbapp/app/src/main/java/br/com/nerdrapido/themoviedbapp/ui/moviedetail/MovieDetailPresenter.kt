package br.com.nerdrapido.themoviedbapp.ui.moviedetail

import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.ui.abstracts.Presenter

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
interface MovieDetailPresenter: Presenter<MovieDetailView> {


    fun setMovie(movieListResultObject: MovieListResultObject)

}