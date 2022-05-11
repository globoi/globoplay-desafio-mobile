package com.ftoniolo.globoplay.framework.imageLoader

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.glide.transformations.BlurTransformation
import javax.inject.Inject

class GlideImageLoader @Inject constructor() : ImageLoader {
    override fun load(imageView: ImageView, imageUrl: String, placeholder: Int, fallback: Int) {
        Glide.with(imageView.rootView)
            .load(imageUrl)
            .fallback(fallback)
            .into(imageView)
    }

    @Suppress("MagicNumber")
    override fun loadWithBlur(
        imageView: ImageView,
        imageUrl: String,
        placeholder: Int,
        fallback: Int
    ) {
        Glide.with(imageView.rootView)
            .load(imageUrl)
            .apply(bitmapTransform(BlurTransformation(25, 3)))
            .fallback(fallback)
            .into(imageView)
    }
}