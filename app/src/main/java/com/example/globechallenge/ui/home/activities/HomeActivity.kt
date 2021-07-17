package com.example.globechallenge.ui.home.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.globechallenge.data.repository.HomeRepository
import com.example.globechallenge.databinding.ActivityHomeBinding
import com.example.globechallenge.ui.home.adapters.HomeGenreAdapter
import com.example.globechallenge.ui.home.viewmodels.HomeViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var recyclerViewGenre: RecyclerView
    private lateinit var viewModel: HomeViewModel
    private val adapterGenre = HomeGenreAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {
        viewBind()
        setupViewModel()
        setupActionBar()
        setupRecyclerGenre()
        viewModel.getMovieByGenre()
    }

    private fun setupActionBar() {
        supportActionBar?.hide()
    }

    private fun setupRecyclerGenre() {
        recyclerViewGenre.run {
            layoutManager = LinearLayoutManager(this@HomeActivity, RecyclerView.VERTICAL, false)
            adapter = adapterGenre
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, HomeViewModel.HomeViewModelFactory(HomeRepository())).get(
            HomeViewModel::class.java)
        viewModel.movieByGenreLiveData.observe(this) {
            adapterGenre.addMovieToGenre(it)
        }
    }

    private fun viewBind() {
        recyclerViewGenre = binding.rvHomeGenre
    }
}