package com.app.fakegloboplay.features.commons.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.app.fakegloboplay.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class ImageLoaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyle, defStyleRes) {

    private val img: ImageView
    private val progressBar: ProgressBar

    init {
        inflate(context, R.layout.image_loader_view, this)
        img = this.findViewById(R.id.image_loader_item)
        progressBar = this.findViewById(R.id.image_loader_progress_bar)
    }

    fun setScaleType(scaleType: ImageView.ScaleType) {
        img.scaleType = scaleType
    }

    fun loadImageCircle(string: String) {
        progressBar.visibility = View.VISIBLE
        Glide.with(this)
            .load(string)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }
            })
            .circleCrop()
            .into(img)
    }

    fun loadImage(string: String) {
        progressBar.visibility = View.VISIBLE
        Glide.with(this)
            .load(string)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }
            })
            .into(img)
    }
}
