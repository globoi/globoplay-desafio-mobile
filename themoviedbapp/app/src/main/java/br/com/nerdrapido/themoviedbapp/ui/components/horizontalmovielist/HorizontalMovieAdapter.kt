package br.com.nerdrapido.themoviedbapp.ui.components.horizontalmovielist

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.Window
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.ui.components.abstracts.MovieListAdapter


/**
 * Created By FELIPE GUSBERTI @ 12/03/2020
 */
class HorizontalMovieAdapter(
    private val data: MutableList<MovieListResultObject>,
    private val context: Context
) : MovieListAdapter<HorizontalMovieViewHolder>(data, context) {

    override fun getViewHolder(itemView: View): HorizontalMovieViewHolder =
        HorizontalMovieViewHolder(itemView)

    override fun getViewHolderLayoutId() = R.layout.view_movie_card_horizontal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalMovieViewHolder {
        val view = super.onCreateViewHolder(parent, viewType)
        val displayMetrics = context.resources.displayMetrics
        val height = displayMetrics.heightPixels
        view.poster.layoutParams.height = height / 4
        return view
    }
}