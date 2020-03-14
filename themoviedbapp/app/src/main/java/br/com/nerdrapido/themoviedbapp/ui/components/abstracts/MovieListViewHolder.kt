package br.com.nerdrapido.themoviedbapp.ui.components.abstracts

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.nerdrapido.themoviedbapp.R

/**
 * Created By FELIPE GUSBERTI @ 14/03/2020
 */
abstract class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract val poster: ImageView

}