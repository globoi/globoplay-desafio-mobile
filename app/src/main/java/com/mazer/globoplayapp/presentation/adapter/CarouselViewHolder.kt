package com.mazer.globoplayapp.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mazer.globoplayapp.databinding.ItemMovieBinding
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.utils.AppConstants.BASE_IMG_URL_SMALL

class CarouselViewHolder  (private val binding: ItemMovieBinding, private val onMovieSelected: (movie: Movie) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        Glide.with(binding.root.context).load(BASE_IMG_URL_SMALL+movie.posterPath).into(binding.ivPosterMovie)
        binding.root.setOnClickListener {
            onMovieSelected.invoke(movie)
        }
    }
}

