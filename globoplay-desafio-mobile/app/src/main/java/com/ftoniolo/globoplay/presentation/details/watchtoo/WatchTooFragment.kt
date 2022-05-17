package com.ftoniolo.globoplay.presentation.details.watchtoo

import android.os.Bundle
import android.util.Log
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
import com.ftoniolo.globoplay.presentation.details.TabViewPagerArgs
import com.ftoniolo.globoplay.presentation.home.HomeLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WatchTooFragment : Fragment {
    private var filmId: Long? = null
    constructor() : super()
    constructor(filmId: Long){
        this.filmId = filmId
    }

    private var _binding: FragmentWatchTooBinding? = null
    private val binding: FragmentWatchTooBinding get() = _binding!!

    private val watchTooGridAdapter: WatchTooGridAdapter by lazy {
        WatchTooGridAdapter(imageLoader)
    }

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

        initFilmAdapter()
        observeInitialLoadState()

        viewModel.state.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is WatchTooViewModel.UiState.SearchResult -> {
                    lifecycleScope.launch {
                        watchTooGridAdapter.submitData(uiState.data)
                    }
                }
            }
        }
        id.let {
            viewModel.showFilms(filmId!!)
        }
    }

    @Suppress("MagicNumber")
    private fun initFilmAdapter() {
        postponeEnterTransition()
        with(binding.rvWatchToo) {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)
            adapter = watchTooGridAdapter.withLoadStateFooter(
                footer = HomeLoadStateAdapter(
                    watchTooGridAdapter::retry
                )
            )
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
    }

    private fun observeInitialLoadState() {
        lifecycleScope.launch {
            watchTooGridAdapter.loadStateFlow.collectLatest { loadState ->
                binding.flipperWatchToo.displayedChild = when (loadState.refresh) {
                    is LoadState.Loading -> {
                        Log.d(WatchTooFragment::class.simpleName, loadState.toString())
                        setShimmerVisibility(true)
                        FLIPPER_CHILD_LOADING
                    }
                    is LoadState.NotLoading -> {
                        if (loadState.append.endOfPaginationReached && watchTooGridAdapter.itemCount < 1) {
                            FLIPPER_CHILD_EMPTY
                        } else {
                            Log.d(WatchTooFragment::class.simpleName, loadState.toString())
                            setShimmerVisibility(false)
                            FLIPPER_CHILD_FILMS
                        }
                    }
                    is LoadState.Error -> {
                        setShimmerVisibility(false)
                        binding.includeViewFilmsErrorState.buttonRetry.setOnClickListener {
                            watchTooGridAdapter.retry()
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
        private const val FLIPPER_CHILD_EMPTY = 3
    }

}