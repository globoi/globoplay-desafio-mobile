package com.ftoniolo.globoplay.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.globoplay.databinding.ItemFilmBinding
import com.ftoniolo.globoplay.framework.imageLoader.ImageLoader

class FilmGridViewHolder(
    itemFilmBinding: ItemFilmBinding,
    private val imageLoader: ImageLoader,
    private val onItemClick:(film: Film, view: View) -> Unit
) : RecyclerView.ViewHolder(itemFilmBinding.root) {

    private val itemFilm = itemFilmBinding.itemPoster

    fun bind(film: Film){
        itemFilm.transitionName = film.title

        imageLoader.load(
            itemFilm, film.imageUrl
        )


        itemView.setOnClickListener {
            onItemClick.invoke(film, itemFilm)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            imageLoader: ImageLoader,
            onItemClick:(film: Film, view: View) -> Unit
        ): FilmGridViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemFilmBinding.inflate(inflater, parent, false)
            return FilmGridViewHolder(itemBinding, imageLoader ,onItemClick)
        }
    }
}