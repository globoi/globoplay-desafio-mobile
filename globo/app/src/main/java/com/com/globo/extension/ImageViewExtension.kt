package com.com.globo.extension

import android.widget.ImageView
import com.com.globo.BuildConfig
import com.com.globo.R
import com.com.globo.repository.model.Movie

fun ImageView.loadImageMovie(movie: Movie) {
    setBackgroundResource(R.color.grey)
    com.bumptech.glide.Glide
        .with(this)
        .load(BuildConfig.BASE_URL_FILE + movie.posterPath)
        .into(this)
}