package com.nroncari.movieplay.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadWallpaper(
    path: String,
) {
    Picasso.get()
        .load("https://image.tmdb.org/t/p/original/$path")
        .into(this)
}