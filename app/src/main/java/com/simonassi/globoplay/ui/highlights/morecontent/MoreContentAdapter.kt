package com.simonassi.globoplay.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.simonassi.globoplay.data.movie.Movie
import androidx.recyclerview.widget.ListAdapter
import com.simonassi.globoplay.BuildConfig
import com.simonassi.globoplay.databinding.ListItemCardMovieBinding
import com.simonassi.globoplay.ui.highlights.morecontent.MoreContentFragment
import com.simonassi.globoplay.utilities.contants.ImageQualitySpec

class MoreContentAdapter : ListAdapter<Movie, RecyclerView.ViewHolder>(MoreContentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MoreContentViewHolder(
            ListItemCardMovieBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = getItem(position)
        (holder as MoreContentViewHolder).bind(movie)
    }

    class MoreContentViewHolder(
        private val binding: ListItemCardMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {}
        fun bind(item: Movie) {
            binding.apply {
                item.cover = BuildConfig.BUCKET_URL + ImageQualitySpec.LOW_QUALITY + item.cover
                item.backdropCover = BuildConfig.BUCKET_URL + ImageQualitySpec.MID_QUALITY + item.cover
                moreContentItem = item
                executePendingBindings()
            }
        }
    }
}

private class MoreContentDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}