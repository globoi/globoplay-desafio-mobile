package com.com.globo.details

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.com.globo.R
import com.com.globo.extension.loadImageMovie
import com.com.globo.helper.TransitionHelper
import com.com.globo.repository.model.Movie

class MovieSeeMoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.item_image)
    private lateinit var movie: Movie

    init {
        image.setOnClickListener {
            val activity = image.context as Activity

            val intent = Intent(image.context, MovieDetailsActivity::class.java)
            intent.putParcelableArrayListExtra(
                MovieDetailsActivity.EXTRA_LIST_MOVIE,
                activity.intent.getParcelableArrayListExtra(MovieDetailsActivity.EXTRA_LIST_MOVIE)
            )
            intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE, movie)

            TransitionHelper.startActivityWithTransition(
                activity, intent, Pair(image, "item_image")
            )
        }
    }

    fun bindItem(movie: Movie) {
        this.movie = movie
        image.loadImageMovie(movie)
    }
}