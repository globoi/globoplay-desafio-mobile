package com.globo.moviesapp.ui.movie.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.globo.moviesapp.R
import com.globo.moviesapp.ui.movie.adapter.MovieAdapter
import com.globo.moviesapp.ui.movie.viewmodel.MovieViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_genre_movies.*
import kotlinx.android.synthetic.main.fragment_movies_favorite.*
import kotlinx.android.synthetic.main.fragment_movies_favorite.viewFailConnection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieFavoriteFragment: DaggerFragment() {
    companion object {
        fun newInstance(): MovieFavoriteFragment {
            return MovieFavoriteFragment()
        }
    }

    lateinit var movieViewModel: MovieViewModel
    lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupReciclerView()
        setupViewModel()
    }

    private fun setupReciclerView() {
        adapter = MovieAdapter()
        rvMovieFavorite.layoutManager = GridLayoutManager(context, 3)
        rvMovieFavorite.adapter = adapter
    }

    private fun setupViewModel() {
        movieViewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)

        movieViewModel.moviesFavorite.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }

        movieViewModel.isLoading.observe(viewLifecycleOwner){
            updateIsLoading(it)
        }

        movieViewModel.error.observe(viewLifecycleOwner){
            updateIsErrors(!it.isNullOrBlank())
        }

        CoroutineScope(Dispatchers.IO).launch {
            movieViewModel.loadMoviesFavorite()
        }
    }
    
    private fun updateIsLoading(status: Boolean){
        if(status) {
            pbFavorite.visibility = View.VISIBLE
            rvMovieFavorite.visibility = View.GONE
        } else {
            pbFavorite.visibility = View.GONE
            rvMovieFavorite.visibility = View.VISIBLE
        }
    }

    private fun updateIsErrors(status: Boolean){
        if(status) {
            viewFailConnection.visibility = View.VISIBLE
            viewLoading.visibility = View.GONE
        } else {
            viewFailConnection.visibility = View.GONE
            viewLoading.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            movieViewModel.loadMoviesFavorite()
        }
    }
}