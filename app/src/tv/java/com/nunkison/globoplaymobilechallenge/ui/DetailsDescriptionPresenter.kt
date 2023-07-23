package com.nunkison.globoplaymobilechallenge.ui

import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import com.nunkison.globoplaymobilechallenge.Movie

class DetailsDescriptionPresenter : AbstractDetailsDescriptionPresenter() {

    override fun onBindDescription(
        viewHolder: AbstractDetailsDescriptionPresenter.ViewHolder,
        item: Any
    ) {
        val movie = item as Movie

        viewHolder.title.text = movie.title
        viewHolder.subtitle.text = movie.studio
        viewHolder.body.text = movie.description
    }
}