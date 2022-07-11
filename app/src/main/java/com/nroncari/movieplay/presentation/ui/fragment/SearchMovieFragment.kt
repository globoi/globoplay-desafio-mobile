package com.nroncari.movieplay.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nroncari.movieplay.databinding.FragmentMovieSearchBinding
import com.nroncari.movieplay.presentation.ui.adapter.MovieAdapter
import com.nroncari.movieplay.presentation.viewmodel.SearchViewModel
import com.nroncari.movieplay.presentation.viewmodel.StateAppComponentsViewModel
import com.nroncari.movieplay.presentation.viewmodel.VisualComponents
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
        binding.searchButton.setOnClickListener {
            val movieName = binding.searchFormName.text.toString()
            viewModel.searchMovies(movieName)
        }

        viewModel.movies.observe(viewLifecycleOwner) { adapter.submitData(lifecycle, it) }
    }

    private fun goToMovieDetailFragment(movieId: Long) {
        val direction = SearchMovieFragmentDirections
            .actionSearchMovieFragmentToMovieDetailFragment(movieId)
        findNavController().navigate(direction)
    }
}