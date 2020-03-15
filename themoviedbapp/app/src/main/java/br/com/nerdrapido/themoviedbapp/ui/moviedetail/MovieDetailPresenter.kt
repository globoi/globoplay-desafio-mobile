package br.com.nerdrapido.themoviedbapp.ui.moviedetail

import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation.NavigationPresenter

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
interface MovieDetailPresenter : NavigationPresenter<MovieDetailView> {


    fun setMovie(movieListResultObject: MovieListResultObject)

    suspend fun loadRelatedMoviePage(page: Int): List<MovieListResultObject>

}