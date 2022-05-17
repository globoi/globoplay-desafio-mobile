package com.simonassi.globoplay.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.simonassi.globoplay.data.movie.Movie
import androidx.recyclerview.widget.ListAdapter
import com.simonassi.globoplay.BuildConfig
import com.simonassi.globoplay.data.tv.Tv
import com.simonassi.globoplay.databinding.ListItemMovieBinding
import com.simonassi.globoplay.utilities.contants.ImageQualitySpec
import com.simonassi.globoplay.utilities.contants.ItemType

class MovieAdapter : PagingDataAdapter<Movie, RecyclerView.ViewHolder>(MovieDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            ListItemMovieBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            (holder as MovieViewHolder).bind(movie)
        }
    }

    class MovieViewHolder(
        private val binding: ListItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.movieItem?.let { movie ->
                    navigateToHighLights(it, movie)
                }
            }
        }

        private fun navigateToHighLights(view: View, movie: Movie) {
            val direction = HomeFragmentDirections.actionHomePagerFragmentToHighlightsActivity(
                movie.id,
                ItemType.MOVIE
            )
            view.findNavController().navigate(direction)
        }

        fun bind(item: Movie) {
            binding.apply {
                item.cover = BuildConfig.BUCKET_URL + ImageQualitySpec.LOW_QUALITY + item.cover
                item.backdropCover = BuildConfig.BUCKET_URL + ImageQualitySpec.MID_QUALITY + item.cover
                movieItem = item
                executePendingBindings()
            }
        }
    }
}

private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}