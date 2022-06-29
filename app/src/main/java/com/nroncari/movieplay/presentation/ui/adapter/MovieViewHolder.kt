package com.nroncari.movieplay.presentation.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nroncari.movieplay.R
import com.nroncari.movieplay.presentation.model.MovieListItemPresentation
import com.nroncari.movieplay.utils.loadWallpaper

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(movie: MovieListItemPresentation) {
        itemView.findViewById<ImageView>(R.id.item_movie_wallpaper)
            .loadWallpaper(movie.posterPath)
    }
}