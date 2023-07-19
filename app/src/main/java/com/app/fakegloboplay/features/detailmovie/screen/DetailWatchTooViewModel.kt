package com.app.fakegloboplay.features.detailmovie.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fakegloboplay.network.response.MyFavorite
import com.app.fakegloboplay.features.commons.ListMovieViewState
import com.app.fakegloboplay.features.detailmovie.repository.DetailMoveRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailWatchTooViewModel(
    private val repository: DetailMoveRepository
): ViewModel() {

    private val _detailWatchTooState = MutableLiveData<ListMovieViewState>()
    val detailWatchTooState: LiveData<ListMovieViewState> = _detailWatchTooState

    fun getRecommendations(id:Int, page:Int, mediaType:String?) {
        viewModelScope.launch {
            repository.getRecommendations(id, mediaType, page)
                .catch { _ -> _detailWatchTooState.value = ListMovieViewState.Error }
                .collect { listMovie ->
                    listMovie?.let {
                        if (it.isEmpty()) {
                            _detailWatchTooState.value = ListMovieViewState.Empty
                        } else {
                            _detailWatchTooState.value = ListMovieViewState.Success(it)
                        }
                    }
                }
        }
    }

    fun postMyFavorite(myFavorite: MyFavorite) {
        viewModelScope.launch {
            repository.postMyFavorite(myFavorite)
        }
    }
}