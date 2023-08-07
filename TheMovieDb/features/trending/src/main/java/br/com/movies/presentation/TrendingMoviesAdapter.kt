package br.com.movies.presentation

import android.view.ViewGroup
import androidx.paging.DifferCallback
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import br.com.movies.domain.model.TrendingMovies
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class TrendingMoviesAdapter  @Inject constructor()
    : PagingDataAdapter<TrendingMovies, TrendingMovieViewHolder>(DiffCallback){
    var onItemClicked: ((id: Int) -> Unit)? = null

    object DiffCallback : DiffUtil.ItemCallback<TrendingMovies>() {
        override fun areItemsTheSame(old: TrendingMovies, new: TrendingMovies): Boolean = old.id == new.id
        override fun areContentsTheSame(old: TrendingMovies, new: TrendingMovies): Boolean = old == new
    }

    override fun onBindViewHolder(holder: TrendingMovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingMovieViewHolder =
        TrendingMovieViewHolder(parent, onItemClicked)

    }