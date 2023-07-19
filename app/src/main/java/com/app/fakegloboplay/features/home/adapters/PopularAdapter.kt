package com.app.fakegloboplay.features.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.app.fakegloboplay.BuildConfig
import com.app.fakegloboplay.R
import com.app.fakegloboplay.databinding.VhMovieItemBinding
import com.app.fakegloboplay.features.commons.customview.ImageLoaderView
import com.app.fakegloboplay.features.mylist.screen.adapter.OnItemClickListener
import com.app.fakegloboplay.network.response.Movie

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.TvPopularVH>() {

    private lateinit var onItemClickListener: OnItemClickListener

    private val diffDataConfig = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }

    private val diffData = AsyncListDiffer(this@PopularAdapter, diffDataConfig)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvPopularVH =
        TvPopularVH(
            VhMovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun getItemCount(): Int = diffData.currentList.size

    private fun getItem(position: Int) = diffData.currentList[position]

    fun submitList(list: List<Movie>) {
        diffData.submitList(list)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onBindViewHolder(holder: TvPopularVH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TvPopularVH(private val binding: VhMovieItemBinding) : ViewHolder(binding.root) {

        fun bind(item: Movie) {
            with(binding) {
                root.setOnClickListener {
                    onItemClickListener.onItemClick(item, absoluteAdapterPosition)
                }
                imgPoster.loadImage(BuildConfig.URL_IMG + item.posterPath)
            }
        }
    }
}

