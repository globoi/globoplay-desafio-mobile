package com.example.globechallenge.utils

import android.content.Context
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.globechallenge.data.network.Api
import com.example.globechallenge.data.response.Cast
import com.example.globechallenge.data.response.GenresItemDetail
import jp.wasabeef.glide.transformations.BlurTransformation


fun ImageView.loadImage(imageUrl: String, blur: Boolean = false) {
    val glide = Glide.with(this)
        .load(Api.IMAGE_SERVICE_BASE + imageUrl)
        .centerCrop()
    if (blur) glide.apply(RequestOptions.bitmapTransform(BlurTransformation(20, 1)))
    glide.into(this)
}

fun Button.setDrawable(context: Context, id: Int) {
    this.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, id), null, null, null)
}

fun List<GenresItemDetail>.concatGenre(): String {
    var string = ""
    var first = true
    this.forEach {
        if (first) {
            string = it.name
            first = false
        } else{
            string += ", ${it.name}"
        }
    }
    return string
}

fun List<Cast>.concatCast(): String {
    var string = ""
    var first = true
    this.forEach {
        if (first) {
            string = it.originalName
            first = false
        } else{
            string += ", ${it.originalName}"
        }
    }
    return string
}
