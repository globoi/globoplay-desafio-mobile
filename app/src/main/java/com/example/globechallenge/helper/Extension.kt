package com.example.globechallenge.helper

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.globechallenge.data.network.Api
import jp.wasabeef.glide.transformations.BlurTransformation

fun ImageView.loadImage(imageUrl: String, blur: Boolean = false) {
    val glide = Glide.with(this)
        .load(Api.IMAGESERVICE + imageUrl)
        .centerCrop()
    if (blur) glide.apply(RequestOptions.bitmapTransform(BlurTransformation(20, 1)))
    glide.into(this)
}
