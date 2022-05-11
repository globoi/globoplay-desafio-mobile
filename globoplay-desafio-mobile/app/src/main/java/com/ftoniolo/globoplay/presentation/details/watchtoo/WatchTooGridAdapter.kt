package com.ftoniolo.globoplay.presentation.details.watchtoo

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ftoniolo.core.domain.model.WatchToo
import com.ftoniolo.globoplay.framework.imageLoader.ImageLoader
import javax.inject.Inject

class WatchTooGridAdapter @Inject constructor(
    private val imageLoader: ImageLoader
) : PagingDataAdapter<WatchToo, WatchTooGridViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchTooGridViewHolder {
        return WatchTooGridViewHolder.create(parent, imageLoader)
    }

    override fun onBindViewHolder(holder: WatchTooGridViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<WatchToo>() {
            override fun areItemsTheSame(
                oldItem: WatchToo,
                newItem: WatchToo
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: WatchToo,
                newItem: WatchToo
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}