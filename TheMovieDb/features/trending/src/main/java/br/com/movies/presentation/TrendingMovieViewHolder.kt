package br.com.movies.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.movies.databinding.ItemTrendingMovieBinding
import br.com.movies.domain.model.TrendingMovies
import com.bumptech.glide.Glide

class TrendingMovieViewHolder(
    private val parent: ViewGroup,
    private val onItemClicked: ((id: Int) -> Unit)?,
) : BaseViewHolder<TrendingMovies,ItemTrendingMovieBinding>(
    binding = ItemTrendingMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
) {
    override fun bind(item: TrendingMovies) {
        with(binding) {
            txtTitle.text = item.title
            txtReleaseDate.text = item.releaseDate
            txtVoteAverage.text = item.voteAverage
            Glide.with(parent.context).load(item.posterPath).centerCrop().into(imgMovie)


        }
    }
}