package com.com.ifood.main.view

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.com.ifood.R
import com.com.ifood.details.MovieDetailsActivity
import com.com.ifood.extension.loadImageMovie
import com.com.ifood.helper.TransitionHelper.startActivityWithTransition
import com.com.ifood.repository.model.Movie

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
        image.setBackgroundResource(R.color.grey)
        image.loadImageMovie(movie)
    }
}