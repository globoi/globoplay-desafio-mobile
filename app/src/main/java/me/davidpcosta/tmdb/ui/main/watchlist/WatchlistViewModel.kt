package me.davidpcosta.tmdb.ui.main.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.davidpcosta.tmdb.data.repository.WatchlistRepository
import me.davidpcosta.tmdb.data.model.Movie

class WatchlistViewModel(private val watchlistRepository: WatchlistRepository) : ViewModel() {

    val movies: LiveData<List<Movie>> = MutableLiveData()

    fun fetchWatchlist(accountId: Long, sessionId: String) {
        watchlistRepository.watchlist(accountId, sessionId).subscribe {
            movies as MutableLiveData
            movies.value = it.results
        }
    }
}