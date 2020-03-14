package br.com.nerdrapido.themoviedbapp.ui.components.horizontalmovielist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.nerdrapido.themoviedbapp.R


/**
 * Created By FELIPE GUSBERTI @ 12/03/2020
 */
class HorizontalMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val poster = itemView.findViewById<View>(R.id.posterIv) as ImageView

}