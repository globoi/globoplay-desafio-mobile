package com.ftoniolo.globoplay.presentation.home

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ftoniolo.core.domain.model.FilmsPoster

class HomeHorizontalAdapter : ListAdapter<FilmsPoster, HomeHorizontalViewHolder>(diffCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHorizontalViewHolder {
        return  HomeHorizontalViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HomeHorizontalViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<FilmsPoster>(){
            override fun areItemsTheSame(
                oldItem: FilmsPoster,
                newItem: FilmsPoster
            ): Boolean {
                return oldItem.posterUrl == newItem.posterUrl
            }

            override fun areContentsTheSame(
                oldItem: FilmsPoster,
                newItem: FilmsPoster
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}