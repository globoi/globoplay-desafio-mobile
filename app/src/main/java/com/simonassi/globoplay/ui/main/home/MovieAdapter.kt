package com.simonassi.globoplay.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.simonassi.globoplay.data.Movie
import androidx.recyclerview.widget.ListAdapter
import com.simonassi.globoplay.BuildConfig
import com.simonassi.globoplay.databinding.ListItemMovieBinding
import com.simonassi.globoplay.utilities.ImageQualitySpec

class MovieAdapter : ListAdapter<Movie, RecyclerView.ViewHolder>(MovieDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            ListItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = getItem(position)
        (holder as MovieViewHolder).bind(movie)
    }

    class MovieViewHolder(
        private val binding: ListItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {

            }
        }

        fun bind(item: Movie) {
            binding.apply {
                imageCover = BuildConfig.BUCKET_URL + ImageQualitySpec.LOW_QUALITY + item.cover
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