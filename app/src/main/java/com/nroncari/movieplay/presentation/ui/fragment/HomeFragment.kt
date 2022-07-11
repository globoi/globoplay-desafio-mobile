package com.nroncari.movieplay.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.nroncari.movieplay.databinding.FragmentHomeBinding
import com.nroncari.movieplay.presentation.model.MovieListItemPresentation
import com.nroncari.movieplay.presentation.ui.adapter.MovieAdapter
import com.nroncari.movieplay.presentation.viewmodel.HomeViewModel
import com.nroncari.movieplay.presentation.viewmodel.StateAppComponentsViewModel
import com.nroncari.movieplay.presentation.viewmodel.VisualComponents
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val appComponentsViewModel: StateAppComponentsViewModel by sharedViewModel()
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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        appComponentsViewModel.havComponent = VisualComponents(true)

        binding()
        listeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun binding() {
        binding.networkErrorAnimation.setAnimation("anim/network_error.json")
        binding.rvActionMovies.adapter = actionMovieAdapter
        binding.rvAnimationMovies.adapter = animationMovieAdapter
        binding.rvComedyMovies.adapter = comedyMoveAdapter
        binding.rvDramaMovies.adapter = dramaMovieAdapter
        binding.rvHorrorMovies.adapter = horrorMovieAdapter
    }

    private fun listeners() {
        viewModel.getMovies()
        configAdapter(viewModel.actionMovies, actionMovieAdapter)
        configAdapter(viewModel.animationMovies, animationMovieAdapter)
        configAdapter(viewModel.comedyMovies, comedyMoveAdapter)
        configAdapter(viewModel.dramaMovies, dramaMovieAdapter)
        configAdapter(viewModel.horrorMovies, horrorMovieAdapter)
        binding.searchButton.setOnClickListener {
            goToSearchMovieFragment()
        }
    }

    private fun configAdapter(
        genreMovies: LiveData<PagingData<MovieListItemPresentation>>,
        movieAdapter: MovieAdapter
    ) {
        genreMovies.observe(viewLifecycleOwner) {
            movieAdapter.submitData(lifecycle, it)
            lifecycleScope.launch {
                movieAdapter.loadStateFlow.collectLatest { loadStates ->
                    when (loadStates.refresh) {
                        is LoadState.Error -> {
                            val errorMessage: String? = (loadStates.refresh as LoadState.Error).error.message
                            initNetworkAnimationError(errorMessage!!)
                        }
                    }
                }
            }
        }
    }

    private fun goToSearchMovieFragment() {
        val direction = HomeFragmentDirections
            .actionHomeFragmentToSearchMovieFragment()
        navController.navigate(direction)
    }

    private fun goToMovieDetailFragment(movieId: Long) {
        val direction = HomeFragmentDirections
            .actionHomeFragmentToMovieDetailFragment(movieId)
        navController.navigate(direction)
    }

    private fun initNetworkAnimationError(messageError: String) {
        with(binding.networkErrorAnimation) {
            scaleX = 0.5f
            scaleY = 0.5f
            visibility = VISIBLE
            playAnimation()
        }
        binding.errorMsgMovies.visibility = VISIBLE
        binding.errorMsgMovies.text = messageError
        hideFields()
    }

    private fun hideFields() {
        binding.actionMsgMovies.visibility = INVISIBLE
        binding.animationMsgMovies.visibility = INVISIBLE
        binding.comedyMsgMovies.visibility = INVISIBLE
        binding.dramaMsgMovies.visibility = INVISIBLE
        binding.horrorMsgMovies.visibility = INVISIBLE
    }
}