package com.reisdeveloper.globoplay.ui.features.mylist

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.reisdeveloper.globoplay.R
import com.reisdeveloper.globoplay.base.BaseFragment
import com.reisdeveloper.globoplay.databinding.FragmentMyListBinding
import com.reisdeveloper.globoplay.ui.components.ShimmerLoadingView
import com.reisdeveloper.globoplay.ui.features.movie.main.MovieDetailsFragment.Companion.EXTRA_MOVIE
import com.reisdeveloper.globoplay.ui.uiModel.MovieUiModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyListFragment : BaseFragment<FragmentMyListBinding, MyListViewModel>(
    FragmentMyListBinding::inflate
) {

    override val viewModel: MyListViewModel by viewModel()

    private val myListAdapter = MyListAdapter(object : MyListAdapter.Listener {
        override fun onItemClick(movie: MovieUiModel) {
            findNavController().navigate(
                R.id.action_navigation_favorite_movies_to_navigation_movie_details,
                Bundle().apply {
                    putParcelable(EXTRA_MOVIE, movie)
                }
            )
        }
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()

        setupAdapter()

        //TODO implementar uma forma de pegar esse account id
        viewModel.getUserList("20244782")
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.screen.collectLatest { state ->
                when (state) {
                    is MyListViewModel.Screen.Error -> {

                    }
                    is MyListViewModel.Screen.Loading -> {
                        onLoading(
                            binding.contentMyList,
                            R.layout.shimmer_favorite_movies,
                            state.loading
                        )
                    }
                    is MyListViewModel.Screen.FavoriteMovies -> {
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

    private fun onLoading(
        viewGroup: ViewGroup,
        loadLayout: Int,
        loading: Boolean
    ) {
        if (loading) {
            ShimmerLoadingView.show(viewGroup, loadLayout)
        } else {
            ShimmerLoadingView.hide(viewGroup)
        }
    }
}