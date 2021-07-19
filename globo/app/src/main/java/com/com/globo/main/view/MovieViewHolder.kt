package com.com.globo.main.view

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.com.globo.R
import com.com.globo.details.MovieDetailsActivity
import com.com.globo.extension.loadImageMovie
import com.com.globo.helper.TransitionHelper.startActivityWithTransition
import com.com.globo.repository.model.Movie

class MovieViewHolder(itemView: View, listMovies: List<Movie>) : RecyclerView.ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.item_image)
    private lateinit var movie: Movie

    init {
        image.setOnClickListener {
            val intent = Intent(itemView.context, MovieDetailsActivity::class.java)
            intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE, movie)
            intent.putParcelableArrayListExtra(
                MovieDetailsActivity.EXTRA_LIST_MOVIE,
                ArrayList(listMovies)
            )
            startActivityWithTransition(
                it.context as Activity,
                intent,
                Pair(it, "item_image")
            )
        }
    }

    fun bindItem(movie: Movie) {
        this.movie = movie
        image.loadImageMovie(movie)
    }
}