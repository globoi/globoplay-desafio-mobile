package com.ftoniolo.globoplay.presentation.home

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ftoniolo.core.domain.model.FilmsFromGenre

class HomeVerticalAdapter : ListAdapter<FilmsFromGenre, HomeVerticalViewHolder>(diffCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVerticalViewHolder {
        return HomeVerticalViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HomeVerticalViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<FilmsFromGenre>() {
            override fun areItemsTheSame(
                oldItem: FilmsFromGenre,
                newItem: FilmsFromGenre
            ): Boolean {
                return oldItem.genre == newItem.genre
            }

            override fun areContentsTheSame(
                oldItem: FilmsFromGenre,
                newItem: FilmsFromGenre
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}