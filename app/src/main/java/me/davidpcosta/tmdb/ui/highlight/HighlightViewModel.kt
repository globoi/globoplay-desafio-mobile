package me.davidpcosta.tmdb.ui.highlight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.davidpcosta.tmdb.data.model.Cast
import me.davidpcosta.tmdb.data.model.Movie
import me.davidpcosta.tmdb.data.repository.MoviesRepository

class HighlightViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    val similarMovies: LiveData<List<Movie>> = MutableLiveData()
    val cast: LiveData<List<Cast>> = MutableLiveData()

    fun fetchSimilarMovies(movieId: Long) {
        moviesRepository.similarMovies(movieId).subscribe {
            similarMovies as MutableLiveData
            similarMovies.value = it.results
        }
    }

    fun fetchCredits(movieId: Long) {
        moviesRepository.credits(movieId).subscribe {
            cast as MutableLiveData
            cast.value = it.cast
        }
    }
}