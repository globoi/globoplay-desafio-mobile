package com.reisdeveloper.globoplay.ui.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.reisdeveloper.globoplay.R
import com.reisdeveloper.globoplay.base.BASE_IMAGE_URL
import com.reisdeveloper.globoplay.databinding.ItemMovieBinding
import com.reisdeveloper.globoplay.ui.uiModel.MovieUiModel
import com.reisdeveloper.globoplay.extensions.toPx

class MovieListAdapter(
    private val listener: Listener
) : PagingDataAdapter<MovieUiModel, MovieListAdapter.MovieViewHolder>(ARTICLE_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val tile = getItem(position)
        if (tile != null) {
            holder.bind(tile)
            holder.itemView.setOnClickListener {
                listener.onClickItem(tile)
            }
        }
    }

    companion object {
        private val ARTICLE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieUiModel>() {
            override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean =
                oldItem == newItem
        }
    }

    interface Listener {
        fun onClickItem(item: MovieUiModel)
    }

    class MovieViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieUiModel) {
            Glide.with(binding.root.context)
                .load("$BASE_IMAGE_URL${movie.posterPath}")
                .placeholder(R.drawable.bg_holder)
                .error(R.drawable.bg_holder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(
                    RequestOptions().transform(
                        RoundedCorners(12.toPx(binding.root.context))
                    )
                )
                .into(binding.imgItemMyList)
        }
    }
}