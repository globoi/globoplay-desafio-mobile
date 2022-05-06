package com.ftoniolo.globoplay.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ftoniolo.core.domain.model.FilmsPoster
import com.ftoniolo.globoplay.R
import com.ftoniolo.globoplay.databinding.ItemFilmBinding

class HomeHorizontalViewHolder(
    itemFilmBinding: ItemFilmBinding
) : RecyclerView.ViewHolder(itemFilmBinding.root) {

    private val itemPoster = itemFilmBinding.itemPoster

    fun bind(filmsPoster: FilmsPoster){
        Glide.with(itemView)
            .load(filmsPoster.posterUrl)
            .fallback(R.drawable.ic_img_loading_error)
            .into(itemPoster)
    }
    companion object {
        fun create(parent: ViewGroup): HomeHorizontalViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemFilmBinding.inflate(inflater, parent, false)
            return HomeHorizontalViewHolder(itemBinding)
        }
    }
}