package com.nroncari.movieplay.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nroncari.movieplay.databinding.FragmentDetailsMovieBinding
import com.nroncari.movieplay.presentation.viewmodel.MovieDetailViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailsMovieFragment : Fragment() {

    private var _binding: FragmentDetailsMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieDetailViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listener()
    }

    private fun listener() {
        viewModel.movieOriginalTitle.observe(viewLifecycleOwner) { originalTitle ->
            originalTitle.let { binding.textOriginalTitle.text = it }
        }
        viewModel.movieYear.observe(viewLifecycleOwner) { movieYear ->
            movieYear.let { binding.textYear.text = it }
        }
        viewModel.average.observe(viewLifecycleOwner) { average ->
            if (average != null) {
                binding.fragmentMovieDetailRatingBar.rating = average
                binding.fragmentMovieDetailRatingNumeric.text = "$average"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}