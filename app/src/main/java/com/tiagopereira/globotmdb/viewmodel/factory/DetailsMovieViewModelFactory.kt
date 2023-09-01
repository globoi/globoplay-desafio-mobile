package com.tiagopereira.globotmdb.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tiagopereira.globotmdb.viewmodel.DetailsMovieViewModel
import com.tiagopereira.globotmdb.viewmodel.repository.DetailsRepository

class DetailsMovieViewModelFactory constructor(private val repository: DetailsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DetailsMovieViewModel::class.java)) {
            DetailsMovieViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}