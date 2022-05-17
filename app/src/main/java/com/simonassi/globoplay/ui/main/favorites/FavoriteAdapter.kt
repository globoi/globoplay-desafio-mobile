package com.simonassi.globoplay.ui.main.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.simonassi.globoplay.BuildConfig
import com.simonassi.globoplay.data.favorite.entity.Favorite
import com.simonassi.globoplay.databinding.ListItemFavoriteBinding
import com.simonassi.globoplay.utilities.contants.ImageQualitySpec

class FavoriteAdapter : ListAdapter<Favorite, RecyclerView.ViewHolder>(FavoriteDiffCallback())  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FavoriteViewHolder(
            ListItemFavoriteBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val favorite = getItem(position)
        (holder as FavoriteViewHolder).bind(favorite)
    }

    class FavoriteViewHolder(
        private val binding: ListItemFavoriteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.favoriteItem.let { favoriteItem ->
                    navigateToHighLights(it, favoriteItem!!)
                }
            }
        }

        private fun navigateToHighLights(view: View, favorite: Favorite) {
            val direction = FavoritesFragmentDirections.actionFavoritesPagerFragmentToHighlightsActivity(
                favorite.tmdbId,
                favorite.type!!
            )
            view.findNavController().navigate(direction)
        }

        fun bind(item: Favorite) {
            binding.apply {
                item.cover = BuildConfig.BUCKET_URL + ImageQualitySpec.LOW_QUALITY + item.cover
                favoriteItem = item
                executePendingBindings()
            }
        }
    }
}

private class FavoriteDiffCallback : DiffUtil.ItemCallback<Favorite>() {

    override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
        return oldItem.tmdbId == newItem.tmdbId
    }

    override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
        return oldItem == newItem
    }
}