package com.nroncari.movieplay.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nroncari.movieplay.R
import com.nroncari.movieplay.presentation.model.MovieListItemPresentation
import com.nroncari.movieplay.utils.loadWallpaper

class MovieAdapter(
    var onItemClickListener: (movieId: Long) -> Unit = {}
) :
    PagingDataAdapter<MovieListItemPresentation, MovieAdapter.MovieViewHolder>(MovieListDiffCallback) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )

    inner class MovieViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        private lateinit var movie: MovieListItemPresentation

        fun bind(movie: MovieListItemPresentation) {
            this.movie = movie
            itemView.findViewById<ImageView>(R.id.item_movie_wallpaper)
                .loadWallpaper(movie.posterPath)
        }

        init {
            itemView.setOnClickListener {
                if (::movie.isInitialized)
                    onItemClickListener(movie.id)
            }
        }
    }
}