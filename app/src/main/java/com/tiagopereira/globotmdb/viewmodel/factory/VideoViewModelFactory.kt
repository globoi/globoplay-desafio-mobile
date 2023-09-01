package com.tiagopereira.globotmdb.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tiagopereira.globotmdb.viewmodel.VideoViewModel
import com.tiagopereira.globotmdb.viewmodel.repository.VideoRepository

class VideoViewModelFactory constructor(private val repository: VideoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(VideoViewModel::class.java)) {
            VideoViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}