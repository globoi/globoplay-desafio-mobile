package com.reisdeveloper.globoplay.ui.features.movie.watchtoo

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.reisdeveloper.globoplay.R
import com.reisdeveloper.globoplay.base.BaseFragment
import com.reisdeveloper.globoplay.databinding.FragmentMyListBinding
import com.reisdeveloper.globoplay.ui.features.movie.main.MovieDetailsFragment
import com.reisdeveloper.globoplay.ui.features.mylist.MyListAdapter
import com.reisdeveloper.globoplay.ui.uiModel.MovieUiModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WatchTooFragment : BaseFragment<FragmentMyListBinding, WatchViewModel>(
    FragmentMyListBinding::inflate
) {
    override val viewModel: WatchViewModel by viewModel()

    private lateinit var movieId: String

    private val myListAdapter = MyListAdapter(object : MyListAdapter.Listener {
        override fun onItemClick(movie: MovieUiModel) {
            findNavController().navigate(
                R.id.action_navigation_favorite_movies_to_navigation_movie_details,
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
                        //TODO implementar cenário de erro
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
        binding.rvMyList.adapter = myListAdapter
        binding.rvMyList.layoutManager = GridLayoutManager(binding.rvMyList.context, 3)
    }

    companion object {
        fun newInstance(movieId: String): WatchTooFragment = WatchTooFragment().apply {
            this.movieId = movieId
        }
    }
}