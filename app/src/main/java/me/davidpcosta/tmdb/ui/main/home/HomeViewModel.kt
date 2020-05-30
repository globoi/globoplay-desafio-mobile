package me.davidpcosta.tmdb.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.davidpcosta.tmdb.data.repository.MoviesRepository
import me.davidpcosta.tmdb.data.model.Genre
import me.davidpcosta.tmdb.data.model.Movie

class HomeViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    val genres: LiveData<List<Genre>> = MutableLiveData()

    fun fetchGenres() {
        if (genres.value != null) return
        moviesRepository.genres().subscribe {
            genres as MutableLiveData
            genres.value = it.genres
        }
    }

    fun fetchMoviesByGenre(genreId: Long): LiveData<List<Movie>> {
        val movies = MutableLiveData<List<Movie>>()
        moviesRepository.moviesByGenre(genreId).subscribe {
            movies.value = it.results
        }
        return movies
    }
}