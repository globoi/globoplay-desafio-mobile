package me.davidpcosta.tmdb.ui.main.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.davidpcosta.tmdb.data.repository.WatchlistRepository
import me.davidpcosta.tmdb.data.model.Movie

class WatchlistViewModel(private val watchlistRepository: WatchlistRepository) : ViewModel() {

//    val result: LiveData<Result<Movie>> = MutableLiveData()
    val movies: LiveData<List<Movie>> = MutableLiveData()

    fun fetchWatchlist() {
        watchlistRepository.watchlist().subscribe {
//            result as MutableLiveData
//            result.value = it

            movies as MutableLiveData
            movies.value = it.results
        }
    }
}