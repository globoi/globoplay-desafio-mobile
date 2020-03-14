package br.com.nerdrapido.themoviedbapp.ui.moviedetail

import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieResponse
import br.com.nerdrapido.themoviedbapp.ui.abstracts.View

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
interface MovieDetailView: View {

    fun movieLoaded(movieResponse: MovieResponse)

}