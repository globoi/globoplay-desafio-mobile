package com.app.fakegloboplay.features.detailmovie.screen

import androidx.fragment.app.Fragment

abstract class DetailFragmentViewPage : Fragment() {
    protected var seriesId: Int = 0
    protected var mediaType: String? = "tv"
    abstract fun setParamMediaType(value: String?)
    abstract fun setParamSeriesId(value: Int)
    abstract fun showLoading()
    abstract fun hideLoading()
    abstract fun showMessageError()
}