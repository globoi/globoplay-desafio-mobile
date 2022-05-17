package com.simonassi.globoplay.ui.main.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.simonassi.globoplay.BuildConfig
import com.simonassi.globoplay.data.movie.Movie
import com.simonassi.globoplay.databinding.ListItemSearchResultBinding
import com.simonassi.globoplay.utilities.contants.ImageQualitySpec
import com.simonassi.globoplay.utilities.contants.ItemType


class SearchResultAdapter : ListAdapter<Movie, RecyclerView.ViewHolder>(SearchResultAdapterDiffCallback())  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchResultViewHolder(
            ListItemSearchResultBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = getItem(position)
        (holder as SearchResultViewHolder).bind(movie)
    }

    class SearchResultViewHolder(
        private val binding: ListItemSearchResultBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.searchItem.let { movieItem ->
                    navigateToHighLights(it, movieItem!!)
                }
            }
        }

        private fun navigateToHighLights(view: View, movie: Movie) {
            val direction = SearchFragmentDirections.actionSearchPagerFragmentsToHighlightsActivity(
                movie.id,
                ItemType.MOVIE
            )
            view.findNavController().navigate(direction)
        }

        fun bind(item: Movie) {
            binding.apply {
                item.cover = BuildConfig.BUCKET_URL + ImageQualitySpec.LOW_QUALITY + item.cover
                searchItem = item
                executePendingBindings()
            }
        }
    }
}

private class SearchResultAdapterDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}