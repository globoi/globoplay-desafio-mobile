package me.davidpcosta.tmdb.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.davidpcosta.tmdb.data.api.ApiService
import me.davidpcosta.tmdb.data.repository.MoviesRepository

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                    moviesRepository = MoviesRepository(
                        api = ApiService.instance
                    )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
