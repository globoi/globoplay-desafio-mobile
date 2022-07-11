package com.nroncari.movieplay.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.nroncari.movieplay.databinding.FragmentMovieSearchBinding
import com.nroncari.movieplay.presentation.ui.adapter.MovieAdapter
import com.nroncari.movieplay.presentation.viewmodel.SearchViewModel
import com.nroncari.movieplay.presentation.viewmodel.StateAppComponentsViewModel
import com.nroncari.movieplay.presentation.viewmodel.VisualComponents
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchMovieFragment : Fragment() {

    private var _binding: FragmentMovieSearchBinding? = null
    private val binding get() = _binding!!
    private val appComponentsViewModel: StateAppComponentsViewModel by sharedViewModel()
    private val viewModel: SearchViewModel by viewModel()
    private val adapter = MovieAdapter { goToMovieDetailFragment(it) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        appComponentsViewModel.havComponent = VisualComponents(false)
        binding.rvSearchMovies.adapter = adapter
        listener()
    }

    private fun listener() {
        binding.networkErrorAnimation.setAnimation("anim/network_error.json")

        binding.searchButton.setOnClickListener {
            val movieName = binding.searchFormName.text.toString()
            viewModel.searchMovies(movieName)
        }

        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
            lifecycleScope.launch {
                adapter.loadStateFlow.collectLatest { loadStates ->
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

    private fun goToMovieDetailFragment(movieId: Long) {
        val direction = SearchMovieFragmentDirections
            .actionSearchMovieFragmentToMovieDetailFragment(movieId)
        findNavController().navigate(direction)
    }

    private fun initNetworkAnimationError(messageError: String) {
        with(binding.networkErrorAnimation) {
            scaleX = 0.5f
            scaleY = 0.5f
            visibility = View.VISIBLE
            playAnimation()
        }
        binding.errorMsgMovies.visibility = View.VISIBLE
        binding.errorMsgMovies.text = messageError
    }
}