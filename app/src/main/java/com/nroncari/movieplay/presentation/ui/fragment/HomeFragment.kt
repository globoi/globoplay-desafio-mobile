package com.nroncari.movieplay.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nroncari.movieplay.databinding.FragmentHomeBinding
import com.nroncari.movieplay.presentation.ui.adapter.MovieAdapter
import com.nroncari.movieplay.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val viewModel: HomeViewModel by viewModel()
    private val adapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvMovies.adapter = adapter
        listeners()
    }

    private fun listeners() {

        viewModel.movies.observe(viewLifecycleOwner, {
            adapter.submitData(lifecycle, it)
        })
    }
}