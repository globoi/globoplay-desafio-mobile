package com.reisdeveloper.globoplay.ui.features.home

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.reisdeveloper.globoplay.R
import com.reisdeveloper.globoplay.base.BaseFragment
import com.reisdeveloper.globoplay.databinding.FragmentHomeBinding
import com.reisdeveloper.globoplay.extensions.safeNavigate
import com.reisdeveloper.globoplay.ui.features.movie.details.MovieDetailsFragment
import com.reisdeveloper.globoplay.ui.features.mylist.MyListAdapter
import com.reisdeveloper.globoplay.ui.uiModel.MovieUiModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {

    override val viewModel: HomeViewModel by viewModel()

    private lateinit var sheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private var popularMoviesAdapter = MovieListAdapter(
        object : MovieListAdapter.Listener {
            override fun onClickItem(item: MovieUiModel) {
                openMovieDetails(item)
            }
        }
    )

    private var topRatedMoviesAdapter = MovieListAdapter(
        object : MovieListAdapter.Listener {
            override fun onClickItem(item: MovieUiModel) {
                openMovieDetails(item)
            }
        }
    )

    private var nowPlayingMoviesAdapter = MovieListAdapter(
        object : MovieListAdapter.Listener {
            override fun onClickItem(item: MovieUiModel) {
                openMovieDetails(item)
            }
        }
    )

    private var upcomingMoviesAdapter = MovieListAdapter(
        object : MovieListAdapter.Listener {
            override fun onClickItem(item: MovieUiModel) {
                openMovieDetails(item)
            }
        }
    )

    private val searchListAdapter = MyListAdapter(object : MyListAdapter.Listener {
        override fun onItemClick(movie: MovieUiModel) {
            openMovieDetails(movie)
        }
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapters()

        setupObserver()

        setupSearchView()

        setupSearchResults()

        viewModel.getPopularMovies()
        viewModel.getNowPlayingMovies()
        viewModel.getTopRatedMovies()
        viewModel.getUpcomingMovies()
    }

    private fun setupSearchView() {
        binding.searchHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.searchMovies(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setupSearchResults() {
        sheetBehavior = BottomSheetBehavior.from(binding.includeMovieList.movieList)
        sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        sheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {}

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
    }

    private fun setupAdapters() {
        binding.rvNowPlaying.adapter = nowPlayingMoviesAdapter
        binding.rvNowPlaying.layoutManager =
            LinearLayoutManager(binding.rvNowPlaying.context, LinearLayoutManager.HORIZONTAL, false)

        binding.rvPopular.adapter = popularMoviesAdapter
        binding.rvPopular.layoutManager =
            LinearLayoutManager(binding.rvPopular.context, LinearLayoutManager.HORIZONTAL, false)

        binding.rvTopRated.adapter = topRatedMoviesAdapter
        binding.rvTopRated.layoutManager =
            LinearLayoutManager(binding.rvTopRated.context, LinearLayoutManager.HORIZONTAL, false)

        binding.rvUpcoming.adapter = upcomingMoviesAdapter
        binding.rvUpcoming.layoutManager =
            LinearLayoutManager(binding.rvUpcoming.context, LinearLayoutManager.HORIZONTAL, false)

        with(binding.includeMovieList.rvMyList) {
            adapter = searchListAdapter
            layoutManager = GridLayoutManager(this.context, 3)
            setHasFixedSize(false)
            isNestedScrollingEnabled = false
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getNowPlayingMovies().collectLatest {
                    nowPlayingMoviesAdapter.submitData(it)
                }

                viewModel.getPopularMovies().collectLatest {
                    popularMoviesAdapter.submitData(it)
                }

                viewModel.getTopRatedMovies().collectLatest {
                    topRatedMoviesAdapter.submitData(it)
                }

                viewModel.getUpcomingMovies().collectLatest {
                    upcomingMoviesAdapter.submitData(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                nowPlayingMoviesAdapter.loadStateFlow.collect {
                    binding.prepProgressNowPlaying.isVisible =
                        it.source.prepend is LoadState.Loading
                    binding.appendProgressNowPlaying.isVisible =
                        it.source.append is LoadState.Loading
                }

                popularMoviesAdapter.loadStateFlow.collect {
                    binding.prepProgressNowPlaying.isVisible =
                        it.source.prepend is LoadState.Loading
                    binding.appendProgressNowPlaying.isVisible =
                        it.source.append is LoadState.Loading
                }

                topRatedMoviesAdapter.loadStateFlow.collect {
                    binding.prepProgressNowPlaying.isVisible =
                        it.source.prepend is LoadState.Loading
                    binding.appendProgressNowPlaying.isVisible =
                        it.source.append is LoadState.Loading
                }

                upcomingMoviesAdapter.loadStateFlow.collect {
                    binding.prepProgressNowPlaying.isVisible =
                        it.source.prepend is LoadState.Loading
                    binding.appendProgressNowPlaying.isVisible =
                        it.source.append is LoadState.Loading
                }
            }
        }

        lifecycleScope.launch {
            viewModel.screen.collectLatest { state ->
                when (state) {
                    is HomeViewModel.Screen.Error -> {

                    }
                    is HomeViewModel.Screen.Loading -> {
                        /*onLoading(
                            binding.contentMyList,
                            R.layout.shimmer_favorite_movies,
                            state.loading
                        )*/
                    }
                    is HomeViewModel.Screen.SearchedMovies -> {
                        searchListAdapter.setItems(state.movies)
                        sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }
            }
        }
    }

    private fun openMovieDetails(item: MovieUiModel) {
        findNavController().safeNavigate(
            R.id.action_goto_movie_details,
            Bundle().apply {
                putParcelable(MovieDetailsFragment.EXTRA_MOVIE, item)
            }
        )
    }
}