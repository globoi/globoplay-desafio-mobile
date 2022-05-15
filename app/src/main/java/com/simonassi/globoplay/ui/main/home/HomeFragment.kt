package com.simonassi.globoplay.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.simonassi.globoplay.databinding.FragmentHomeBinding
import com.simonassi.globoplay.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), LifecycleObserver {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getMovies()
        homeViewModel.getTvs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentHomeBinding?
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val movieAdapter = MovieAdapter()
        binding.movieList.adapter = movieAdapter

        val tvAdapter = TvAdapter()
        binding.tvList.adapter = tvAdapter

        subscribeUi(movieAdapter, tvAdapter)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(movieAdapter: MovieAdapter, tvAdapter: TvAdapter) {
        homeViewModel.moviesLiveData.observe(viewLifecycleOwner, Observer { movies ->
            movieAdapter.submitList(movies)
        })

        homeViewModel.tvsLiveData.observe(viewLifecycleOwner, Observer { tvs ->
            tvAdapter.submitList(tvs)
        })
    }
}