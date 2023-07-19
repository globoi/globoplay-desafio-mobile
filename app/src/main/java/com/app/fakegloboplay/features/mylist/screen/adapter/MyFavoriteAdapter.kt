package com.app.fakegloboplay.features.mylist.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.fakegloboplay.BuildConfig
import com.app.fakegloboplay.R
import com.app.fakegloboplay.databinding.VhMovieItemBinding
import com.app.fakegloboplay.features.commons.customview.ImageLoaderView
import com.app.fakegloboplay.network.response.Movie

class MyFavoriteAdapter : RecyclerView.Adapter<MyFavoriteAdapter.MyFavoriteVH>() {

    private lateinit var onItemClickListener: OnItemClickListener

    private val diffDataConfig = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }
    private val diffData = AsyncListDiffer(this, diffDataConfig)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFavoriteVH =
        MyFavoriteVH(
            VhMovieItemBinding.inflate(
                LayoutInflater
                    .from(parent.context), parent, false
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

    override fun onBindViewHolder(holder: MyFavoriteVH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MyFavoriteVH(private val binding: VhMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

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