package br.com.movies.presentation.trending

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import br.com.common.domain.model.Movie
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class TrendingMoviesAdapter  @Inject constructor()
    : PagingDataAdapter<Movie, TrendingMovieViewHolder>(DiffCallback){
    var onItemClicked: ((id: Int) -> Unit)? = null

    object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(old: Movie, new: Movie): Boolean = old.id == new.id
        override fun areContentsTheSame(old: Movie, new: Movie): Boolean = old == new
    }

    override fun onBindViewHolder(holder: TrendingMovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingMovieViewHolder =
        TrendingMovieViewHolder(parent, onItemClicked)

    }