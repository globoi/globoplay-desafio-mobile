package com.reisdeveloper.globoplay.ui.features.movie.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.reisdeveloper.data.dataModel.MovieDetails
import com.reisdeveloper.globoplay.R
import com.reisdeveloper.globoplay.base.BaseFragment
import com.reisdeveloper.globoplay.databinding.FragmentMovieMoreDetailsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieMoreDetailsFragment : BaseFragment<FragmentMovieMoreDetailsBinding, MovieMoreDetailsViewModel>(
    FragmentMovieMoreDetailsBinding::inflate
) {
    override val viewModel: MovieMoreDetailsViewModel by viewModel()

    private lateinit var movieId: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()

        viewModel.getMoreDetails(movieId)
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.screen.collectLatest { state ->
                when (state) {
                    is MovieMoreDetailsViewModel.Screen.Error -> {
                        //TODO implementar cenário de erro
                    }
                    is MovieMoreDetailsViewModel.Screen.Loading -> {
                        shimmerLoading(
                            binding.contentMoreDetails,
                            R.layout.shimmer_favorite_movies,
                            state.loading
                        )
                    }
                    is MovieMoreDetailsViewModel.Screen.MovieMoreDetails -> {
                        setMovieDetails(state.movieDetails)
                    }
                }
            }
        }
    }

    private fun setMovieDetails(movieDetails: MovieDetails) {
        binding.txtMoreDetailsSpecification.text = makeSpecifications(movieDetails)
    }

    private fun makeSpecifications(movieDetails: MovieDetails): String {
        val originalTitle = String.format(getString(R.string.original_title), movieDetails.originalTitle)
        val genre = String.format(getString(R.string.genre), movieDetails.genres)
        val episodes = String.format(getString(R.string.episodes), movieDetails.originalTitle)
        val released = String.format(getString(R.string.released), movieDetails.releaseDate)
        val country = String.format(getString(R.string.country), movieDetails.productionCountries)
        val direction = String.format(getString(R.string.direction), movieDetails.originalTitle)

        return "$originalTitle\n $genre\n $episodes\n $released\n $country\n $direction"
    }

    companion object {
        fun newInstance(movieId: String): MovieMoreDetailsFragment = MovieMoreDetailsFragment().apply {
            this.movieId = movieId
        }
    }
}