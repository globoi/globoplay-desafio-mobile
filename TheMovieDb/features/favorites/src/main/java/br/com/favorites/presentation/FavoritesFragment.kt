package br.com.favorites.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import br.com.common.Extensions.SpacingItemDecoration
import br.com.common.Extensions.collectLatestLifecycleFlow
import br.com.common.Extensions.navigateToMovie
import br.com.common.Extensions.toast
import br.com.favorites.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class FavoritesFragment : Fragment() {

private lateinit var binding: FragmentFavoritesBinding
private val favoritesViewmodel: FavoritesViewModel by viewModels ()
    @Inject
    lateinit var adapter: FavoritesMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        collectLatestLifecycleFlow(favoritesViewmodel.uiState) { launchesUiState ->
            if (launchesUiState is FavoritesUiState.Success) {
                adapter.submitData(launchesUiState.pagingData)
            }

        }

        collectLatestLifecycleFlow(adapter.loadStateFlow) {
            binding.favoritesSwipeRefreshLayout.isRefreshing = it.refresh is LoadState.Loading

            when (it.refresh) {
                is LoadState.Loading -> {
                }
                is LoadState.Error -> {
                    val message = it.refresh as LoadState.Error
                    message.error.message?.let { message ->
                        toast(message)
                    }

                }
                is LoadState.NotLoading -> {

                }
            }
            }


    }

    private fun setupRecyclerView() {
        adapter.onItemClicked = ::openMovieDetail

        binding.favoritesSwipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
        }
        binding.recyclerviewFavorites.addItemDecoration(
            SpacingItemDecoration(resources.getDimensionPixelSize(br.com.ui_kit.R.dimen.space_medium), true)
        )
        binding.recyclerviewFavorites.adapter = adapter
    }


    private fun openMovieDetail(movieId: Int) {

        findNavController().navigateToMovie(this,movieId)
    }
}