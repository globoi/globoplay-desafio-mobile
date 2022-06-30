package com.nroncari.movieplay.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.nroncari.movieplay.databinding.FragmentHomeBinding
import com.nroncari.movieplay.presentation.model.MovieListItemPresentation
import com.nroncari.movieplay.presentation.ui.adapter.MovieAdapter
import com.nroncari.movieplay.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val navController by lazy { NavHostFragment.findNavController(this) }
    private val viewModel: HomeViewModel by viewModel()
    private val actionMovieAdapter = MovieAdapter { goToMovieDetailFragment(it) }
    private val animationMovieAdapter = MovieAdapter { goToMovieDetailFragment(it) }
    private val comedyMoveAdapter = MovieAdapter { goToMovieDetailFragment(it) }
    private val dramaMovieAdapter = MovieAdapter { goToMovieDetailFragment(it) }
    private val horrorMovieAdapter = MovieAdapter { goToMovieDetailFragment(it) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding()

        listeners()
    }

    private fun binding() {
        binding.rvActionMovies.adapter = actionMovieAdapter
        binding.rvAnimationMovies.adapter = animationMovieAdapter
        binding.rvComedyMovies.adapter = comedyMoveAdapter
        binding.rvDramaMovies.adapter = dramaMovieAdapter
        binding.rvHorrorMovies.adapter = horrorMovieAdapter
    }

    private fun listeners() {
        configAdapter(viewModel.actionMovies, actionMovieAdapter)
        configAdapter(viewModel.animationMovies, animationMovieAdapter)
        configAdapter(viewModel.comedyMovies, comedyMoveAdapter)
        configAdapter(viewModel.dramaMovies, dramaMovieAdapter)
        configAdapter(viewModel.horrorMovies, horrorMovieAdapter)
    }

    private fun configAdapter(
        genreMovies: LiveData<PagingData<MovieListItemPresentation>>,
        movieAdapter: MovieAdapter
    ) {
        genreMovies.observe(viewLifecycleOwner) {
            movieAdapter.submitData(lifecycle, it)
        }
    }

    private fun goToMovieDetailFragment(movieId: Long) {
        val direction = HomeFragmentDirections
            .actionHomeFragmentToMovieDetailFragment(movieId)
        navController.navigate(direction)
    }
}