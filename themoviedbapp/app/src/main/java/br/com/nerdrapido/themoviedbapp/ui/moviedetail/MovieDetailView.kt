package br.com.nerdrapido.themoviedbapp.ui.moviedetail

import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieResponse
import br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation.NavigationView

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
interface MovieDetailView : NavigationView {

    fun movieInfoLoaded(movieResponse: MovieResponse)

    fun setMovieListState(isMovieInMyList: Boolean?)

    fun movieVideoLoaded(videoId: String)

}