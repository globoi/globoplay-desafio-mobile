package com.globo.moviesapp.ui.movie.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.globo.moviesapp.R
import com.globo.moviesapp.model.movie.Movie
import com.globo.moviesapp.ui.movie.adapter.MovieAdapter
import com.globo.moviesapp.ui.movie.viewmodel.MovieViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie_watch_too.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieWatchTooFragment: DaggerFragment() {
    companion object {
        var movieId: Int? = null
        fun newInstance(movie: Movie) : MovieWatchTooFragment {
            this.movieId = movie.id
            return MovieWatchTooFragment()
        }
    }

    lateinit var movieViewModel: MovieViewModel
    lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_watch_too, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupReciclerView()
        setupViewModel()
    }

    private fun setupReciclerView(){
        adapter = MovieAdapter()
        rvMovieWatchToo.layoutManager = GridLayoutManager(context, 3)
        rvMovieWatchToo.adapter = adapter
    }

    private fun setupViewModel(){
        movieViewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)

        movieViewModel.moviesRecommendation.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }

        CoroutineScope(Dispatchers.IO).launch {
            movieViewModel.loadMoviesRecommendations(movieId!!)
        }
    }
}