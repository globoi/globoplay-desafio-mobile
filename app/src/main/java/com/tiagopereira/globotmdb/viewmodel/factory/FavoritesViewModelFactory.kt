package com.tiagopereira.globotmdb.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tiagopereira.globotmdb.viewmodel.FavoritesViewModel
import com.tiagopereira.globotmdb.viewmodel.repository.FavoritesRepository

class FavoritesViewModelFactory constructor(private val favoritesRepository: FavoritesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            FavoritesViewModel(this.favoritesRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}