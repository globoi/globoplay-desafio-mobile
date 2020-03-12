package br.com.nerdrapido.themoviedbapp.ui.home.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.nerdrapido.themoviedbapp.R


/**
 * Created By FELIPE GUSBERTI @ 12/03/2020
 */
class DiscoverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val textView = itemView.findViewById<View>(R.id.movieTitle) as TextView

    val poster = itemView.findViewById<View>(R.id.posterIv) as ImageView


}