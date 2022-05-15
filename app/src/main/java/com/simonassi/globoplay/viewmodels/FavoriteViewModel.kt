package com.simonassi.globoplay.viewmodels

import androidx.lifecycle.*
import com.simonassi.globoplay.data.favorite.entity.Favorite
import com.simonassi.globoplay.data.favorite.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository,
) : ViewModel() {

    val favoriteLiveData = MutableLiveData<List<Favorite>>()
    private val observer = Observer<List<Favorite>> {
        favoriteLiveData.value = it
    }

    fun getFavorites() {
        repository.getFavoriteList().observeForever(observer)
    }

    override fun onCleared() {
        repository.getFavoriteList().removeObserver(observer)
        super.onCleared()
    }
}