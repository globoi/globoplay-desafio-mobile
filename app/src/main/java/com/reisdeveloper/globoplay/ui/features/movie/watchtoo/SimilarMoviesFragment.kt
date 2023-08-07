package com.reisdeveloper.globoplay.ui.features.movie.watchtoo

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.reisdeveloper.globoplay.R
import com.reisdeveloper.globoplay.base.BaseFragment
import com.reisdeveloper.globoplay.databinding.FragmentSimilarMoviesBinding
import com.reisdeveloper.globoplay.extensions.safeNavigate
import com.reisdeveloper.globoplay.ui.features.movie.details.MovieDetailsFragment
import com.reisdeveloper.globoplay.ui.features.mylist.MyListAdapter
import com.reisdeveloper.globoplay.ui.uiModel.MovieUiModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SimilarMoviesFragment : BaseFragment<FragmentSimilarMoviesBinding, WatchViewModel>(
    FragmentSimilarMoviesBinding::inflate
) {
    override val viewModel: WatchViewModel by viewModel()

    private lateinit var movieId: String

    private val myListAdapter = MyListAdapter(object : MyListAdapter.Listener {
        override fun onItemClick(movie: MovieUiModel) {
            findNavController().safeNavigate(
                R.id.action_goto_movie_details,
                Bundle().apply {
                    putParcelable(MovieDetailsFragment.EXTRA_MOVIE, movie)
                }
            )
        }
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        setupObserver()

        viewModel.getSimilarMovies(movieId)
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.screen.collectLatest { state ->
                when (state) {
                    is WatchViewModel.Screen.Error -> {
                        showError(getString(R.string.there_was_an_error_loading_similar_movies))
                    }
                    is WatchViewModel.Screen.Loading -> {
                        shimmerLoading(
                            binding.contentMyList,
                            R.layout.shimmer_favorite_movies,
                            state.loading
                        )
                    }
                    is WatchViewModel.Screen.WatchToo -> {
                        myListAdapter.setItems(state.movies)
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        binding.rvSimilarMovies.adapter = myListAdapter
        binding.rvSimilarMovies.layoutManager = GridLayoutManager(binding.rvSimilarMovies.context, 3)
    }

    companion object {
        fun newInstance(movieId: String): SimilarMoviesFragment = SimilarMoviesFragment().apply {
            this.movieId = movieId
        }
    }
}