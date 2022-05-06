package com.ftoniolo.globoplay.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ftoniolo.core.domain.model.FilmsFromGenre
import com.ftoniolo.globoplay.databinding.ItemFilmsFromGenreBinding

class HomeVerticalViewHolder(
    itemFilmsFromGenreBinding: ItemFilmsFromGenreBinding
) : RecyclerView.ViewHolder(itemFilmsFromGenreBinding.root) {

    private val homeHorizontalAdapter = HomeHorizontalAdapter()
    private val itemGenre = itemFilmsFromGenreBinding.txtGenre
    private val rvHorizontal = itemFilmsFromGenreBinding.rvHorizontal

    fun bind(filmsFromGenre: FilmsFromGenre) {
        itemGenre.text = filmsFromGenre.genre
        homeHorizontalAdapter.submitList(filmsFromGenre.rvHorizontal)
        rvHorizontal.run {
            layoutManager = LinearLayoutManager(
                itemView.context, RecyclerView.HORIZONTAL, false
            )
        }
        rvHorizontal.adapter = homeHorizontalAdapter
    }

    companion object {
        fun create(parent: ViewGroup): HomeVerticalViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemFilmsFromGenreBinding.inflate(inflater, parent, false)
            return HomeVerticalViewHolder(itemBinding)
        }
    }
}