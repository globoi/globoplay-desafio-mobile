package com.ftoniolo.globoplay.framework.imageLoader

import android.widget.ImageView
import androidx.annotation.DrawableRes

interface ImageLoader {

    fun load(imageView: ImageView, imageUrl: String, @DrawableRes fallback: Int)

    fun loadWithBlur(imageView: ImageView, imageUrl: String, @DrawableRes fallback: Int)
}