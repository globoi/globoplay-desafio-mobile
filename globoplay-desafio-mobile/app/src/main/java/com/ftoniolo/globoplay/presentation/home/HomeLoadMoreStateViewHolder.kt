package com.ftoniolo.globoplay.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.ftoniolo.globoplay.databinding.ItemFilmsLoadMoreStateBinding

class HomeLoadMoreStateViewHolder(
    itemBinding: ItemFilmsLoadMoreStateBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {

    private val binding = ItemFilmsLoadMoreStateBinding.bind(itemView)
    private val progressBarLoadingMore = binding.progressLoadingMore
    private val textTryAgainMessage = binding.textTryAgain.also {
            it.setOnClickListener {
                retry()
            }
    }

    fun bind(loadState: LoadState){
        progressBarLoadingMore.isVisible = loadState is LoadState.Loading
        textTryAgainMessage.isVisible = loadState is LoadState.Error
    }

    companion object{
        fun create(parent: ViewGroup, retry: () -> Unit): HomeLoadMoreStateViewHolder {
            val itemBinding = ItemFilmsLoadMoreStateBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            return HomeLoadMoreStateViewHolder(itemBinding, retry)
        }
    }
}