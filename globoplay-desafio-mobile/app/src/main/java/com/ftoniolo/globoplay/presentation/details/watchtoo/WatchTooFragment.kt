package com.ftoniolo.globoplay.presentation.details.watchtoo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.ftoniolo.globoplay.databinding.FragmentWatchTooBinding
import com.ftoniolo.globoplay.framework.imageLoader.ImageLoader
import com.ftoniolo.globoplay.presentation.home.HomeLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WatchTooFragment(private val filmId: Long) : Fragment() {

    private var _binding: FragmentWatchTooBinding? = null
    @Suppress("UnusedPrivateMember")
    private val binding: FragmentWatchTooBinding get() = _binding!!

    private lateinit var watchTooGridAdapter: WatchTooGridAdapter

    private val viewModel: WatchTooViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentWatchTooBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCharactersAdapter()
        observeInitialLoadState()

        lifecycleScope.launch {
            viewModel.watchTooPagingData(filmId).collect { pagingData ->
                watchTooGridAdapter.submitData(pagingData)
            }
        }
    }

    @Suppress("MagicNumber")
    private fun initCharactersAdapter() {
        watchTooGridAdapter = WatchTooGridAdapter(imageLoader)
        with(binding.rvWatchToo) {
            scrollToPosition(0)
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)
            adapter = watchTooGridAdapter.withLoadStateFooter(
                footer = HomeLoadStateAdapter(
                    watchTooGridAdapter::retry
                )
            )
        }
    }

    private fun observeInitialLoadState() {
        lifecycleScope.launch {
            watchTooGridAdapter.loadStateFlow.collectLatest { loadState ->
                binding.flipperWatchToo.displayedChild = when (loadState.refresh) {
                    is LoadState.Loading -> {
                        setShimmerVisibility(true)
                        FLIPPER_CHILD_LOADING
                    }
                    is LoadState.NotLoading -> {
                        setShimmerVisibility(false)
                        FLIPPER_CHILD_FILMS
                    }
                    is LoadState.Error -> {
                        setShimmerVisibility(false)
                        binding.includeViewFilmsErrorState.buttonRetry.setOnClickListener {
                            watchTooGridAdapter.refresh()
                        }
                        FLIPPER_CHILD_ERROR
                    }
                }
            }
        }
    }

    private fun setShimmerVisibility(visibility: Boolean) {
        binding.includeViewWatchTooLoadingState.shimmerFilms.run {
            isVisible = visibility
            if (visibility) {
                startShimmer()
            } else stopShimmer()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FLIPPER_CHILD_LOADING = 0
        private const val FLIPPER_CHILD_FILMS = 1
        private const val FLIPPER_CHILD_ERROR = 2
    }

}