package br.com.movies.presentation.trending

import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import br.com.ui_kit.R as uiKit

import br.com.movies.databinding.FragmentTrendingMoviesBinding
import br.com.common.Extensions.SpacingItemDecoration
import br.com.common.Extensions.ToolbarUtil
import br.com.common.Extensions.collectLatestLifecycleFlow
import br.com.common.Extensions.hide
import br.com.common.Extensions.navigateToMovie
import br.com.common.Extensions.show
import br.com.common.Extensions.toast
import br.com.ui_kit.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [TrendingMoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class TrendingMoviesFragment : Fragment() {

    private lateinit var binding: FragmentTrendingMoviesBinding
    private val viewModel: TrendingMoviesViewModel by viewModels ()

    @Inject
    lateinit var adapter: TrendingMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrendingMoviesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        subscribeObservers()
        initToolbar()
    }

    private fun subscribeObservers() {
        collectLatestLifecycleFlow(viewModel.uiState) { launchesUiState ->
            if (launchesUiState is TrendingUiState.Success) {
                adapter.submitData(launchesUiState.pagingData)
            }
        }

        collectLatestLifecycleFlow(adapter.loadStateFlow) {
            binding.layoutRecyclerviewTrending.swipeRefreshTrending.isRefreshing = it.refresh is LoadState.Loading

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
        binding.layoutRecyclerviewTrending.swipeRefreshTrending.setOnRefreshListener{
            adapter.refresh()
            loadingShimmer(true)
        }
        binding.layoutRecyclerviewTrending.recyclerviewTrendingMovies.addItemDecoration(
            SpacingItemDecoration(resources.getDimensionPixelSize(R.dimen.space_medium), true)
        )
        binding.layoutRecyclerviewTrending.recyclerviewTrendingMovies.adapter = adapter
    }

    private fun initToolbar() {
        ToolbarUtil.initToolbar(binding.fragmentTrendingToolbar.toolbar,
            uiKit.menu.toolbar_main_menu,
            getString(uiKit.string.txt_globoplay)
        )
    }

    private fun loadingShimmer(visible: Boolean) {

        if(visible) {
            binding.shimmerViewContainer.startShimmer()
            binding.shimmerViewContainer.show()
            binding.layoutRecyclerviewTrending.conteinerMovies.hide()
        } else {
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.hide()
            binding.layoutRecyclerviewTrending.conteinerMovies.show()
        }
    }

    private fun openMovieDetail(movieId: Int) {
        findNavController().navigateToMovie(this,movieId)
    }
}