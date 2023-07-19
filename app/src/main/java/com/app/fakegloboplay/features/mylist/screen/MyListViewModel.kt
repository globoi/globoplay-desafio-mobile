package com.app.fakegloboplay.features.mylist.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fakegloboplay.features.commons.ListMovieViewState
import com.app.fakegloboplay.features.mylist.repository.MyListRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MyListViewModel(
    private val repository: MyListRepository
) : ViewModel() {

    private val _myFavoriteState = MutableLiveData<ListMovieViewState>()
    val myFavoriteState: LiveData<ListMovieViewState> = _myFavoriteState

    fun getMyFavorite() {
        viewModelScope.launch {
            repository.getMyFavorite()
                .catch { _ -> _myFavoriteState.value = ListMovieViewState.Error }
                .collect {
                    it?.let {
                        if (it.isEmpty()) {
                            _myFavoriteState.value = ListMovieViewState.Empty
                        } else {
                            _myFavoriteState.value = ListMovieViewState.Success(it)
                        }
                    }
                }
        }
    }

}