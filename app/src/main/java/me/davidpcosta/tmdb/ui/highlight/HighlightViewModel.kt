package me.davidpcosta.tmdb.ui.highlight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.davidpcosta.tmdb.data.model.Cast
import me.davidpcosta.tmdb.data.model.Movie
import me.davidpcosta.tmdb.data.model.MovieDetails
import me.davidpcosta.tmdb.data.model.WatchlistOperationResponse
import me.davidpcosta.tmdb.data.repository.MoviesRepository
import me.davidpcosta.tmdb.data.repository.WatchlistRepository

class HighlightViewModel(private val moviesRepository: MoviesRepository, private val watchlistRepository: WatchlistRepository) : ViewModel() {

    val watchlistOperationResponse: LiveData<WatchlistOperationResponse> = MutableLiveData()
    val similarMovies: LiveData<List<Movie>> = MutableLiveData()
    val movieDetails: LiveData<MovieDetails> = MutableLiveData()
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

    fun movieDetails(movieId: Long) {
        moviesRepository.movieDetails(movieId).subscribe {
            movieDetails as MutableLiveData
            movieDetails.value = it
        }
    }

    fun addToWatchlist(accountId: Long, sessionId: String, movie: Movie) {
        watchlistRepository.addToWatchlist(accountId, sessionId, movie).subscribe {
            watchlistOperationResponse as MutableLiveData
            watchlistOperationResponse.value = it
        }
    }

    fun removeFromWatchlist(accountId: Long, sessionId: String, movieId: Long) {
        watchlistRepository.removeFromWatchlist(accountId, sessionId, movieId).subscribe {
            watchlistOperationResponse as MutableLiveData
            watchlistOperationResponse.value = it
        }
    }
}