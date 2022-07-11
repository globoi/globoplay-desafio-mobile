package com.nroncari.movieplay.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nroncari.movieplay.databinding.FragmentRecommendedMoviesBinding
import com.nroncari.movieplay.presentation.ui.adapter.MovieAdapter
import com.nroncari.movieplay.presentation.viewmodel.MovieDetailViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListRecommendationsFragment : Fragment() {

    private var _binding: FragmentRecommendedMoviesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieDetailViewModel by sharedViewModel()
    private val adapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommendedMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvRecommendedMovies.adapter = adapter
        listeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun listeners() {
        viewModel.listRecommendations.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }
        adapter.onItemClickListener = { movieId ->
            viewModel.getMovieDetail(movieId)
            viewModel.getMovieDataVideo(movieId)
        }
    }
}