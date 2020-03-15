package br.com.nerdrapido.themoviedbapp.ui.components.verticamovielist

import android.content.Context
import android.view.View
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.ui.components.abstracts.MovieListAdapter


/**
 * Created By FELIPE GUSBERTI @ 12/03/2020
 */
class VerticalMovieAdapter(
    private val data: MutableList<MovieListResultObject>,
    private val context: Context
) : MovieListAdapter<VerticalMovieViewHolder>(data, context) {


    override fun getViewHolder(itemView: View): VerticalMovieViewHolder =
        VerticalMovieViewHolder(itemView)

    override fun getViewHolderLayoutId() = R.layout.view_movie_card_vertical
}