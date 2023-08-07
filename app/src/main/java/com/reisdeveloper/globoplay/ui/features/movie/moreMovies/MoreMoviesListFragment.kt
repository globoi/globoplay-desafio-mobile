package com.reisdeveloper.globoplay.ui.features.movie.moreMovies

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.reisdeveloper.domain.MovieListType
import com.reisdeveloper.globoplay.R
import com.reisdeveloper.globoplay.base.BaseFragment
import com.reisdeveloper.globoplay.databinding.FragmentMyListBinding
import com.reisdeveloper.globoplay.extensions.safeNavigate
import com.reisdeveloper.globoplay.ui.features.home.MovieListAdapter
import com.reisdeveloper.globoplay.ui.features.movie.details.MovieDetailsFragment
import com.reisdeveloper.globoplay.ui.uiModel.MovieUiModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoreMoviesListFragment : BaseFragment<FragmentMyListBinding, MoreMoviesListViewModel>(
    FragmentMyListBinding::inflate
) {

    override val viewModel: MoreMoviesListViewModel by viewModel()

    private val moreMoviesAdapter = MovieListAdapter(object : MovieListAdapter.Listener {
        override fun onClickItem(item: MovieUiModel) {
            findNavController().safeNavigate(
                R.id.action_goto_movie_details,
                Bundle().apply {
                    putParcelable(MovieDetailsFragment.EXTRA_MOVIE, item)
                }
            )
        }
    })

    private val movieType by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(EXTRA_MOVIE_TYPE, MovieListType::class.java)
        } else {
            arguments?.getSerializable(EXTRA_MOVIE_TYPE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()

        setupAdapter()

        viewModel.getMoreMoviesByType(movieType as MovieListType)

        binding.textView.text = when(movieType as MovieListType) {
            MovieListType.NOW_PLAYING -> getString(R.string.now_playing)
            MovieListType.POPULAR -> getString(R.string.popular)
            MovieListType.TOP_RATED -> getString(R.string.top_rated)
            MovieListType.UPCOMING -> getString(R.string.upcoming)
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getMoreMoviesByType(movieType as MovieListType).collectLatest {
                    moreMoviesAdapter.submitData(it)
                }
            }
        }
    }

    private fun setupAdapter() {
        binding.rvMyList.adapter = moreMoviesAdapter
        binding.rvMyList.layoutManager = GridLayoutManager(binding.rvMyList.context, 3)
    }

    companion object {
        const val EXTRA_MOVIE_TYPE = "EXTRA_MOVIE_TYPE"
    }
}