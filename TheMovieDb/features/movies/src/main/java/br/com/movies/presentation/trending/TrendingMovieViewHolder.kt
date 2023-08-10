package br.com.movies.presentation.trending

import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.movies.databinding.ItemTrendingMovieBinding
import br.com.common.domain.model.Movie
import br.com.common.base.BaseViewHolder
import com.bumptech.glide.Glide

class TrendingMovieViewHolder(
    private val parent: ViewGroup,
    private val onItemClicked: ((id: Int) -> Unit)?,
) : BaseViewHolder<Movie, ItemTrendingMovieBinding>(
    binding = ItemTrendingMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
) {
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