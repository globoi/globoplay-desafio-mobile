package me.davidpcosta.tmdb.ui.highlight

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import me.davidpcosta.tmdb.data.api.ApiService
import me.davidpcosta.tmdb.data.dao.AppDatabase
import me.davidpcosta.tmdb.data.repository.MoviesRepository
import me.davidpcosta.tmdb.data.repository.WatchlistRepository

@Suppress("UNCHECKED_CAST")
class HighlightViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HighlightViewModel::class.java)) {
            return HighlightViewModel(
                    moviesRepository = MoviesRepository(
                        api = ApiService.instance
                    ),
                    watchlistRepository = WatchlistRepository(
                        api = ApiService.instance,
                        movieDao = Room.databaseBuilder(
                            context,
                            AppDatabase::class.java, "database-watchlist"
                        ).allowMainThreadQueries().build().movieDao()
                    )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
