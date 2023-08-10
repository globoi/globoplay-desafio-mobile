package br.com.favorites.presentation

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import br.com.common.domain.model.Movie
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject


@FragmentScoped
class FavoritesMoviesAdapter  @Inject constructor()
    : PagingDataAdapter<Movie, FavoritesViewHolder>(DiffCallback){
    var onItemClicked: ((id: Int) -> Unit)? = null

    object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(old: Movie, new: Movie): Boolean = old.id == new.id
        override fun areContentsTheSame(old: Movie, new: Movie): Boolean = old == new
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder =
        FavoritesViewHolder(parent, onItemClicked)

}