package com.reisdeveloper.globoplay.ui.features.home

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.reisdeveloper.domain.MovieListType
import com.reisdeveloper.globoplay.R
import com.reisdeveloper.globoplay.base.BaseFragment
import com.reisdeveloper.globoplay.databinding.FragmentHomeBinding
import com.reisdeveloper.globoplay.extensions.safeNavigate
import com.reisdeveloper.globoplay.ui.features.movie.details.MovieDetailsFragment
import com.reisdeveloper.globoplay.ui.features.movie.moreMovies.MoreMoviesListFragment.Companion.EXTRA_MOVIE_TYPE
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

    private var nowPlayingMoviesAdapter = HomeMovieListAdapter(
        object : HomeMovieListAdapter.Listener {
            override fun onItemClick(movie: MovieUiModel) {
                openMovieDetails(movie)
            }
        }
    )

    private var popularMoviesAdapter = HomeMovieListAdapter(
        object : HomeMovieListAdapter.Listener {
            override fun onItemClick(movie: MovieUiModel) {
                openMovieDetails(movie)
            }
        }
    )

    private var topRatedMoviesAdapter = HomeMovieListAdapter(
        object : HomeMovieListAdapter.Listener {
            override fun onItemClick(movie: MovieUiModel) {
                openMovieDetails(movie)
            }
        }
    )

    private var upcomingMoviesAdapter = HomeMovieListAdapter(
        object : HomeMovieListAdapter.Listener {
            override fun onItemClick(movie: MovieUiModel) {
                openMovieDetails(movie)
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

        setupClickMore()

        setupAdapters()

        setupObserver()

        setupSearchView()

        setupSearchResults()

        viewModel.getNowPlayingMovies()
        viewModel.getPopularMovies()
        viewModel.getTopRatedMovies()
        viewModel.getUpcomingMovies()
    }

    private fun setupClickMore() {
        binding.txtHomeNowPlayingMore.setOnClickListener { openMoreMovies(MovieListType.NOW_PLAYING) }
        binding.txtHomePopularMore.setOnClickListener { openMoreMovies(MovieListType.POPULAR) }
        binding.txtTopRatedMore.setOnClickListener { openMoreMovies(MovieListType.TOP_RATED) }
        binding.txtUpcomingMore.setOnClickListener { openMoreMovies(MovieListType.UPCOMING) }
    }

    private fun openMoreMovies(movieListType: MovieListType) {
        findNavController().safeNavigate(
            R.id.action_navigation_home_to_navigation_more_movies,
            Bundle().apply {
                putSerializable(EXTRA_MOVIE_TYPE, movieListType)
            }
        )
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
            viewModel.screen.collectLatest { state ->
                when (state) {
                    is HomeViewModel.Screen.Error -> {
                        sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                        showError(getString(R.string.there_was_an_error_to_serach_movies))
                    }
                    is HomeViewModel.Screen.LoadingNowPlaying -> shimmerLoading(
                            binding.contentNowPlaying,
                            R.layout.shimmer_home_movie_list,
                            state.loading
                        )
                    is HomeViewModel.Screen.LoadingPopular -> shimmerLoading(
                            binding.contentPopular,
                            R.layout.shimmer_home_movie_list,
                            state.loading
                        )
                    is HomeViewModel.Screen.LoadingTopRated -> shimmerLoading(
                            binding.contentTopRated,
                            R.layout.shimmer_home_movie_list,
                            state.loading
                        )
                    is HomeViewModel.Screen.LoadingUpcoming -> shimmerLoading(
                        binding.contentUpcoming,
                        R.layout.shimmer_home_movie_list,
                        state.loading
                    )

                    is HomeViewModel.Screen.LoadingSearch -> {
                        sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                        shimmerLoading(
                            binding.includeMovieList.movieList,
                            R.layout.shimmer_favorite_movies,
                            state.loading
                        )
                    }
                    is HomeViewModel.Screen.SearchedMovies -> {
                        searchListAdapter.setItems(state.movies)
                    }

                    is HomeViewModel.Screen.NowPlayingList -> {
                        nowPlayingMoviesAdapter.setItems(state.movies)
                    }
                    is HomeViewModel.Screen.PopularList -> {
                        popularMoviesAdapter.setItems(state.movies)
                    }

                    is HomeViewModel.Screen.TopRatedList -> {
                        topRatedMoviesAdapter.setItems(state.movies)
                    }
                    is HomeViewModel.Screen.UpcomingList -> {
                        upcomingMoviesAdapter.setItems(state.movies)
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