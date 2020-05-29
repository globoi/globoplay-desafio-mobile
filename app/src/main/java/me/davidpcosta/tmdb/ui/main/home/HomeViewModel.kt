package me.davidpcosta.tmdb.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.davidpcosta.tmdb.data.MoviesRepository
import me.davidpcosta.tmdb.data.model.Genre

class HomeViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    val genres: LiveData<List<Genre>> = MutableLiveData()

    fun fetchGeneres() {
        moviesRepository.generes().subscribe {
            genres as MutableLiveData
            genres.value = it.genres
        }
    }
}