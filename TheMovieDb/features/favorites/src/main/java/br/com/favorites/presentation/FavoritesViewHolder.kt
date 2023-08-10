package br.com.favorites.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.common.base.BaseViewHolder
import br.com.common.domain.model.Movie
import br.com.favorites.databinding.ItemFavoritesMovieBinding
import com.bumptech.glide.Glide


class FavoritesViewHolder(
    private val parent: ViewGroup,
    private val onItemClicked: ((id: Int) -> Unit)?,
) : BaseViewHolder<Movie, ItemFavoritesMovieBinding>(
    binding = ItemFavoritesMovieBinding.inflate(LayoutInflater.from(parent.context),parent, false)) {
    override fun bind(item: Movie) {
                with(binding) {
                    txtTitle.text = item.title
                    txtReleaseDate.text = item.releaseDate
                    txtVoteAverage.text = item.voteAverage
                    Glide.with(parent.context).load(item.posterPath).centerCrop().into(imgMovie)

                    root.setOnClickListener {
                        onItemClicked?.invoke(item.id!!)
                    }
        }
    }
}

