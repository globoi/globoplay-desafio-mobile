package com.example.globechallenge.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.globechallenge.data.repository.home.HomeRepository
import com.example.globechallenge.databinding.FragmentHomeBinding
import com.example.globechallenge.ui.home.adapters.HomeGenreAdapter
import com.example.globechallenge.ui.home.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerViewGenre: RecyclerView
    private lateinit var viewModel: HomeViewModel
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
        viewModel.viewFlipperLiveData.observe(viewLifecycleOwner, {
            it?.let { viewFlipper ->
                setupViewFlipperToShowError(viewFlipper)
            }
        })

        viewModel.movieByGenreMutableLiveData.observe(viewLifecycleOwner, {
            adapterGenre.addMovieToGenre(it)
        })
    }

    private fun setupViewFlipperToShowError(viewFlipper: Pair<Int, Int?>) {
        with(binding) {
            viewFlipperHome.displayedChild = viewFlipper.first
            viewFlipper.second?.let { errorMessageResId ->
                errorDialog.dialogPhrase.text = getString(errorMessageResId)
            }
        }
    }

    private fun getMovieByGenre() {
        viewModel.getMovieByGenre()
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
            viewModel.getMovieByGenre()
        }
    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProvider(this, HomeViewModel.HomeViewModelFactory(HomeRepository())).get(
                HomeViewModel::class.java
            )
    }
}