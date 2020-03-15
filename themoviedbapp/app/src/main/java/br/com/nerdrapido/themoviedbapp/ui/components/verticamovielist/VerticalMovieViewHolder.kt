package br.com.nerdrapido.themoviedbapp.ui.components.verticamovielist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.ui.components.abstracts.MovieListViewHolder


/**
 * Created By FELIPE GUSBERTI @ 12/03/2020
 */
class VerticalMovieViewHolder(itemView: View) : MovieListViewHolder(itemView) {

    override val poster = itemView.findViewById<View>(R.id.posterIv) as ImageView

}