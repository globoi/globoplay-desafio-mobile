package com.example.globechallenge.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.globechallenge.data.repository.home.HomeRepositoryImplementation
import com.example.globechallenge.databinding.FragmentHomeBinding
import com.example.globechallenge.ui.home.adapters.HomeGenreAdapter
import com.example.globechallenge.ui.home.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerViewGenre: RecyclerView
    private lateinit var homeViewModel: HomeViewModel
    private val adapterGenre = HomeGenreAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        viewBind()
        setupViewModel()
        setupRecyclerGenre()
        observers()
        getMovieByGenre()
    }

    private fun observers() {
        homeViewModel.viewFlipperLiveData.observe(viewLifecycleOwner, {
            it?.let { viewFlipper ->
                setupViewFlipper(viewFlipper)
            }
        })

        homeViewModel.movieByGenreMutableLiveData.observe(viewLifecycleOwner, {
            adapterGenre.addMovieToGenre(it)
        })
    }

    private fun setupViewFlipper(viewFlipper: Pair<Int, Int?>) {
        with(binding) {
            viewFlipperHome.displayedChild = viewFlipper.first
            viewFlipper.second?.let { errorMessageResId ->
                errorDialog.dialogPhrase.text = getString(errorMessageResId)
            }
        }
    }

    private fun getMovieByGenre() {
        homeViewModel.getMovieByGenre()
    }

    private fun setupRecyclerGenre() {
        recyclerViewGenre.run {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = adapterGenre
        }
    }

    private fun viewBind() {
        recyclerViewGenre = binding.rvHomeGenre
        binding.errorDialog.btnTryAgain.setOnClickListener {
            homeViewModel.getMovieByGenre()
        }
    }

    private fun setupViewModel() {
        homeViewModel =
            ViewModelProvider(this, HomeViewModel.HomeViewModelFactory(HomeRepositoryImplementation())).get(
                HomeViewModel::class.java
            )
    }
}