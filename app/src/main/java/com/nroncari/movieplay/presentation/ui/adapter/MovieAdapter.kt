package com.nroncari.movieplay.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.nroncari.movieplay.R
import com.nroncari.movieplay.presentation.model.MovieListItemPresentation

class MovieAdapter :
    PagingDataAdapter<MovieListItemPresentation, MovieViewHolder>(MovieComparator) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )
}