package com.ftoniolo.globoplay.presentation.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ftoniolo.globoplay.databinding.ItemFilmGridLayoutBinding
import com.ftoniolo.globoplay.framework.imageLoader.ImageLoader
import javax.inject.Inject

class FavoritesGridAdapter @Inject constructor(
    private val filmsFavorite: List<FilmItem>,
    private val imageLoader: ImageLoader,
) : RecyclerView.Adapter<FavoritesGridAdapter.HomeChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeChildViewHolder {
        return HomeChildViewHolder.create(parent, imageLoader)
    }

    override fun onBindViewHolder(holder: HomeChildViewHolder, position: Int) {
        holder.bind(filmsFavorite[position])
    }

    override fun getItemCount() = filmsFavorite.size

    class HomeChildViewHolder(
        itemBinding: ItemFilmGridLayoutBinding,
        private val imageLoader: ImageLoader,
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private val itemFilm = itemBinding.imageCardFilm

        fun bind(filmFavorite: FilmItem) {
            imageLoader.load(
                itemFilm, filmFavorite.imageUrl
            )
        }

        companion object {
            fun create(
                parent: ViewGroup,
                imageLoader: ImageLoader,
            ): HomeChildViewHolder {
                val itemBinding = ItemFilmGridLayoutBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)

                return HomeChildViewHolder(itemBinding, imageLoader)
            }
        }
    }
}