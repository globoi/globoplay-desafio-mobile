package com.nroncari.movieplay.presentation.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nroncari.movieplay.presentation.model.MovieListItemPresentation

object MovieComparator : DiffUtil.ItemCallback<MovieListItemPresentation>() {

    override fun areItemsTheSame(
        oldItem: MovieListItemPresentation,
        newItem: MovieListItemPresentation
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: MovieListItemPresentation,
        newItem: MovieListItemPresentation
    ): Boolean = oldItem.id == newItem.id
}