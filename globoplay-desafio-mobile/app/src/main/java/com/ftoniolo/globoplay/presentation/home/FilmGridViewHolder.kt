package com.ftoniolo.globoplay.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.globoplay.R
import com.ftoniolo.globoplay.databinding.ItemFilmBinding

class FilmGridViewHolder(
    itemFilmBinding: ItemFilmBinding
) : RecyclerView.ViewHolder(itemFilmBinding.root) {

    private val itemFilm = itemFilmBinding.itemPoster
    private val itemText = itemFilmBinding.itemNameFilm

    fun bind(film: Film){
        Glide.with(itemView)
            .load(film.imageUrl)
            .fallback(R.drawable.ic_img_loading_error)
            .into(itemFilm)

        itemText.text = film.title
    }

    companion object {
        fun create(parent: ViewGroup): FilmGridViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemFilmBinding.inflate(inflater, parent, false)
            return FilmGridViewHolder(itemBinding)
        }
    }
}