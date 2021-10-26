package com.com.ifood.extension

import android.widget.ImageView
import com.com.ifood.BuildConfig
import com.com.ifood.R
import com.com.ifood.repository.model.Movie

fun ImageView.loadImageMovie(movie: Movie) {
    setBackgroundResource(R.color.grey)
    com.bumptech.glide.Glide
        .with(this)
        .load(BuildConfig.BASE_URL_FILE + movie.posterPath)
        .into(this)
}