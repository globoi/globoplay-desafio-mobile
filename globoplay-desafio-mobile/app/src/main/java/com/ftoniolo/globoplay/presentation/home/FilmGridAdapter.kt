package com.ftoniolo.globoplay.presentation.home

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ftoniolo.core.domain.model.Film

class FilmGridAdapter : PagingDataAdapter<Film, FilmGridViewHolder>(diffCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmGridViewHolder {
        return FilmGridViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FilmGridViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Film>() {
            override fun areItemsTheSame(
                oldItem: Film,
                newItem: Film
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Film,
                newItem: Film
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}