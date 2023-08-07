package com.reisdeveloper.globoplay.ui.features.movie.moreDetails

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.reisdeveloper.data.dataModel.MovieDetails
import com.reisdeveloper.globoplay.R
import com.reisdeveloper.globoplay.base.BaseFragment
import com.reisdeveloper.globoplay.databinding.FragmentMovieMoreDetailsBinding
import com.reisdeveloper.globoplay.extensions.DATE_FORMAT_DD_MM_YYYY
import com.reisdeveloper.globoplay.extensions.YYYY_MM_DD
import com.reisdeveloper.globoplay.extensions.toDateTimeString
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.util.Locale


class MovieMoreDetailsFragment :
    BaseFragment<FragmentMovieMoreDetailsBinding, MovieMoreDetailsViewModel>(
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
                        showError(getString(R.string.there_was_an_error_loading_video_details))
                    }

                    is MovieMoreDetailsViewModel.Screen.Loading -> {
                        onLoading(state.loading)
                    }

                    is MovieMoreDetailsViewModel.Screen.MovieMoreDetails -> {
                        setMovieDetails(state.movieDetails)
                    }
                }
            }
        }
    }

    private fun onLoading(loading: Boolean) {
        binding.groupMoreDetails.isVisible = !loading
        binding.progressMoreDetails.isVisible = loading
    }

    private fun setMovieDetails(movieDetails: MovieDetails) {
        binding.txtMoreDetails.text = makeSpecifications(movieDetails)
    }

    private fun makeSpecifications(movieDetails: MovieDetails): String {
        val originalTitle =
            String.format(getString(R.string.original_title), movieDetails.originalTitle)
        val genre = String.format(
            getString(R.string.genre),
            movieDetails.genres.joinToString(",", postfix = " ") { it.name }
        )
        val released = String.format(
            getString(R.string.released),
            movieDetails.releaseDate.toDateTimeString(YYYY_MM_DD, DATE_FORMAT_DD_MM_YYYY)
        )
        val country = String.format(
            getString(R.string.country),
            movieDetails.productionCountries.joinToString(",", postfix = " ") { it.name }
        )
        val budget = String.format(
            getString(R.string.budget),
            NumberFormat.getCurrencyInstance(Locale.getDefault()).format(movieDetails.budget)
        )

        return "$originalTitle\n$genre\n$released\n$country\n$budget"
    }

    companion object {
        fun newInstance(movieId: String): MovieMoreDetailsFragment =
            MovieMoreDetailsFragment().apply {
                this.movieId = movieId
            }
    }
}