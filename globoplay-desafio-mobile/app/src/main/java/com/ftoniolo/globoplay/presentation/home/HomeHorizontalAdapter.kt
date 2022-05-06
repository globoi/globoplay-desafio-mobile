package com.ftoniolo.globoplay.presentation.home

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ftoniolo.core.domain.model.FilmPoster

class HomeHorizontalAdapter : ListAdapter<FilmPoster, HomeHorizontalViewHolder>(diffCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHorizontalViewHolder {
        return  HomeHorizontalViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HomeHorizontalViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<FilmPoster>(){
            override fun areItemsTheSame(
                oldItem: FilmPoster,
                newItem: FilmPoster
            ): Boolean {
                return oldItem.posterUrl == newItem.posterUrl
            }

            override fun areContentsTheSame(
                oldItem: FilmPoster,
                newItem: FilmPoster
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}