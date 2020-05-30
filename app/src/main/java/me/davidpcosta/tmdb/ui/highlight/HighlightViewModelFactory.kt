package me.davidpcosta.tmdb.ui.highlight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.davidpcosta.tmdb.data.api.ApiService
import me.davidpcosta.tmdb.data.repository.MoviesRepository
import me.davidpcosta.tmdb.data.repository.WatchlistRepository

@Suppress("UNCHECKED_CAST")
class HighlightViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HighlightViewModel::class.java)) {
            return HighlightViewModel(
                    moviesRepository = MoviesRepository(
                        api = ApiService.instance
                    )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
