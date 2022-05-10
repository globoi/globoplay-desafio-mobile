package com.ftoniolo.globoplay.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.ftoniolo.globoplay.databinding.FragmentHomeBinding
import com.ftoniolo.globoplay.framework.imageLoader.ImageLoader
import com.ftoniolo.globoplay.presentation.details.DetailsFilmViewArg
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var filmGridAdapter: FilmGridAdapter

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHomeAdapter()
        observeInitialLoadState()

        lifecycleScope.launch {
            viewModel.filmsPagingData().collect { pagingData ->
                filmGridAdapter.submitData(pagingData)
            }
        }
    }

    @Suppress("MagicNumber")
    private fun initHomeAdapter() {
        filmGridAdapter = FilmGridAdapter(imageLoader) { film, view ->

            val navExtras = FragmentNavigatorExtras(
                view to film.title
            )
            val directions = HomeFragmentDirections
                .actionHomeFragmentToDetailsFragment(
                    DetailsFilmViewArg(
                        id = film.id,
                        overview = film.overview,
                        title = film.title,
                        imageUrl = film.imageUrl,
                        releaseDate = film.releaseDate)
                )
            findNavController().navigate(directions, navExtras)
        }

        with(binding.rvVertical) {
            scrollToPosition(0)
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)
            adapter = filmGridAdapter.withLoadStateFooter(
                footer = HomeLoadStateAdapter(
                    filmGridAdapter::retry
                )
            )
        }
    }

    private fun observeInitialLoadState() {
        lifecycleScope.launch {
            filmGridAdapter.loadStateFlow.collectLatest { loadState ->
                binding.flipperFilms.displayedChild = when (loadState.refresh) {
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
                            filmGridAdapter.refresh()
                        }
                        FLIPPER_CHILD_ERROR
                    }
                }
            }
        }
    }

    private fun setShimmerVisibility(visibility: Boolean) {
        binding.includeViewFilmLoadingState.shimmerFilms.run {
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