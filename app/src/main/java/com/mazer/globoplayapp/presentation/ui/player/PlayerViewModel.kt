package com.mazer.globoplayapp.presentation.ui.player

import android.os.Build
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.domain.entities.Video
import com.mazer.globoplayapp.domain.use_cases.GetMovieListUseCase
import com.mazer.globoplayapp.presentation.wrapper.VideoUI
import com.mazer.globoplayapp.utils.AppConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel(private val getMovieListUseCase: GetMovieListUseCase): ViewModel() {

    private val _videoList = MutableLiveData<ArrayList<VideoUI>>()
    val videoList: LiveData<ArrayList<VideoUI>> = _videoList

    fun setExtras(bundle: Bundle){
        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(AppConstants.VIDEO_EXTRA, Movie::class.java)
        } else {
            bundle.getParcelable(AppConstants.VIDEO_EXTRA)
        }
        getVideoList(movie?.id)
    }

    private fun getVideoList(movieId: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            movieId?.let {
                _videoList.postValue(getMovieListUseCase.getVideoList(it))
            }
        }
    }

    fun setVideoPlaying(video: Video) {
        _videoList.value?.toList()?.forEach {
            it.isPlaying = false
            if (it.video == video)
                it.isPlaying = true
        }
        _videoList.postValue(_videoList.value)
    }
}