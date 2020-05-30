package me.davidpcosta.tmdb.ui.main.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.davidpcosta.tmdb.data.api.ApiService
import me.davidpcosta.tmdb.data.repository.WatchlistRepository

@Suppress("UNCHECKED_CAST")
class WatchlistViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WatchlistViewModel::class.java)) {
            return WatchlistViewModel(
                    watchlistRepository = WatchlistRepository(
                        api = ApiService.instance
                    )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
