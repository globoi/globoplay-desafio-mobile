package com.tiagopereira.globotmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiagopereira.globotmdb.data.VideoResponse
import com.tiagopereira.globotmdb.viewmodel.repository.VideoRepository
import kotlinx.coroutines.launch

class VideoViewModel constructor(private val repository: VideoRepository) : ViewModel() {

    private val _trailerMovie = MutableLiveData<VideoResponse>()
    val trailerMovie: LiveData<VideoResponse> = _trailerMovie

    private val _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean> = _showError

    fun getTrailer(idMovie: Int) = viewModelScope.launch {
        val response = repository.getTrailerById(idMovie)
        if (response.isSuccessful) {
            _trailerMovie.postValue(response.body())
        } else {
            _showError.postValue(true)
        }
    }

}