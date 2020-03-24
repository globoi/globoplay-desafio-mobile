package br.com.nerdrapido.themoviedbapp.ui.components.horizontalmovielist

import android.view.View
import android.widget.ImageView
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.ui.components.abstracts.MovieListViewHolder


/**
 * Created By FELIPE GUSBERTI @ 12/03/2020
 */
class HorizontalMovieViewHolder(itemView: View) :  MovieListViewHolder(itemView) {

    override val poster = itemView.findViewById<View>(R.id.posterIv) as ImageView

}