package com.tiagopereira.globotmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.tiagopereira.globotmdb.R
import com.tiagopereira.globotmdb.data.ApiResponse
import com.tiagopereira.globotmdb.databinding.ItemMovieBinding
import com.tiagopereira.globotmdb.utils.Constants.Companion.BASE_URL_IMG

class MovieAdapter (private val onItemClicked: (ApiResponse.Result) -> Unit) : PagingDataAdapter<ApiResponse.Result, MovieAdapter.MovieViewHolder>(diffCallback) {

    inner class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ApiResponse.Result>() {
            override fun areItemsTheSame(oldItem: ApiResponse.Result, newItem: ApiResponse.Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ApiResponse.Result, newItem: ApiResponse.Result): Boolean {
                return oldItem == newItem
            }
        }
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.binding.apply {

            val urlImage = BASE_URL_IMG + currentItem?.posterPath

            imgMovie.load(urlImage) {
                crossfade(true)
                crossfade(500)
                error(R.drawable.baseline_theaters_24)
                scale(Scale.FILL)
            }

            cardMovie.setOnClickListener {
                if (currentItem != null) {
                    onItemClicked(currentItem)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}