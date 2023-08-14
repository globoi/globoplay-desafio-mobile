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
import br.com.common.Extensions.ToolbarUtil
import br.com.common.Extensions.collectLatestLifecycleFlow
import br.com.common.Extensions.hide
import br.com.common.Extensions.navigateToMovie
import br.com.common.Extensions.show
import br.com.common.Extensions.toast
import br.com.favorites.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import br.com.ui_kit.R as uiKit


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
        initToolbar()
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
                    loadingShimmer(true)
                }
                is LoadState.Error -> {
                    loadingShimmer(false)
                    val message = it.refresh as LoadState.Error
                    message.error.message?.let { message ->
                        toast(message)
                    }

                }
                is LoadState.NotLoading -> {
                        loadingShimmer(false)
                }
            }
            }


    }

    private fun setupRecyclerView() {
        adapter.onItemClicked = ::openMovieDetail

        binding.favoritesSwipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
            loadingShimmer(true)
        }
        binding.recyclerviewFavorites.addItemDecoration(
            SpacingItemDecoration(resources.getDimensionPixelSize(br.com.ui_kit.R.dimen.space_medium), true)
        )
        binding.recyclerviewFavorites.adapter = adapter
    }

    private fun loadingShimmer(visible: Boolean) {

        if(visible) {
            binding.shimmerView.startShimmer()
            binding.shimmerView.show()
            binding.recyclerviewFavorites.hide()
        } else {
            binding.shimmerView.stopShimmer()
            binding.shimmerView.hide()
            binding.recyclerviewFavorites.show()
        }
    }

    private fun initToolbar() {
        ToolbarUtil.initToolbar(binding.fragmentTrendingToolbar.toolbar,
            uiKit.menu.toolbar_main_menu,
            getString(uiKit.string.txt_favority_fragment)
            )
    }

    private fun openMovieDetail(movieId: Int) {
        findNavController().navigateToMovie(this,movieId)
    }
}