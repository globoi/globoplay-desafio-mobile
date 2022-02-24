package com.globo.moviesapp.ui.genre.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.globo.moviesapp.model.genre.Genre
import com.globo.moviesapp.network.genre.GenreRepository
import java.lang.Exception
import javax.inject.Inject

class GenreViewModel @Inject constructor(
    private val repository: GenreRepository
): ViewModel() {

    var apiKey: String? = null
    var languageLocale: String? = null
    var genres = MutableLiveData<List<Genre>>()
    var error = MutableLiveData<String>()
    var isLoading = MutableLiveData(true)

    fun loadGenre() {
        try {
            this.isLoading.postValue(true)
            val genres = repository.getGenreAll(apiKey!!, languageLocale!!)
            this.genres.postValue(genres)
            this.isLoading.postValue(false)
        } catch (e: Exception){
            error.postValue(e.message)
        }
    }

}