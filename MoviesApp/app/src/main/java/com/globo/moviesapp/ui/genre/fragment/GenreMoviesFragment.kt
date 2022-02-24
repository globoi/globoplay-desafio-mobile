package com.globo.moviesapp.ui.genre.fragment

import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.globo.moviesapp.R
import com.globo.moviesapp.model.genre.Genre
import com.globo.moviesapp.ui.genre.adapter.GenreAdapter
import com.globo.moviesapp.ui.genre.viewmodel.GenreViewModel
import com.globo.moviesapp.ui.movie.viewmodel.MovieViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_genre_movies.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GenreMoviesFragment: DaggerFragment() {
    companion object {
        fun newInstance() : GenreMoviesFragment {
            return GenreMoviesFragment()
        }
    }

    lateinit var genreViewModel: GenreViewModel
    lateinit var movieViewModel: MovieViewModel

    lateinit var adapter: GenreAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_genre_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupReciclerView()
        setupViewModel()
    }

    private fun setupReciclerView(){
        adapter = GenreAdapter()
        rvGenresMovie.layoutManager = LinearLayoutManager(context)
        rvGenresMovie.adapter = adapter
    }

    private fun setupViewModel(){
        genreViewModel = ViewModelProvider(requireActivity()).get(GenreViewModel::class.java)
        movieViewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)

        genreViewModel.isLoading.observe(viewLifecycleOwner){
            updateIsLoading(it)
        }

        genreViewModel.error.observe(viewLifecycleOwner){
            updateIsErrors(!it.isNullOrBlank())
        }

        genreViewModel.genres.observe(viewLifecycleOwner){
            adapter.updateList(it)
            updateMovie(it)
        }

        CoroutineScope(Dispatchers.IO).launch {
            genreViewModel.loadGenre()
        }

        movieViewModel.movies.observe(viewLifecycleOwner){
            adapter.updateMovieList(it)
        }
    }

    private fun updateIsLoading(status: Boolean){
        if(status) {
            viewLoading.visibility = View.VISIBLE
        } else {
            viewLoading.visibility = View.GONE
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

    private fun updateMovie(genres: List<Genre>){
        CoroutineScope(Dispatchers.IO).launch {
            movieViewModel.loadMovies(genres)
        }
    }
}