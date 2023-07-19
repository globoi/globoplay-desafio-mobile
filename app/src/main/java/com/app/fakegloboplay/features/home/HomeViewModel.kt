package com.app.fakegloboplay.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fakegloboplay.features.commons.ListMoviePageViewState
import com.app.fakegloboplay.features.home.repository.HomeRepository
import com.app.fakegloboplay.network.response.AccountResponse
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository,
) : ViewModel() {

    private val _listPopularState = MutableLiveData<ListMoviePageViewState>()
    val listPopularState: LiveData<ListMoviePageViewState> = _listPopularState

    private val _account = MutableLiveData<AccountResponse>()
    val account: LiveData<AccountResponse> = _account

    fun getTvPopular(page:Int) {
        viewModelScope.launch {
            repository.getTvPopular(page)
                .catch { _listPopularState.value = ListMoviePageViewState.Error }
                .collect { listMovie ->
                    listMovie.let {
                        if (it.results.isEmpty()) {
                            _listPopularState.value = ListMoviePageViewState.Empty
                        } else {
                            _listPopularState.value = ListMoviePageViewState.Success(it)
                        }
                    }
                }
        }
    }

    fun getMoviesPopular(page:Int) {
        viewModelScope.launch {
            repository.getMoviesPopular(page)
                .catch { _ -> _listPopularState.value = ListMoviePageViewState.Error }
                .collect { listMovie ->
                    listMovie?.let {
                        if (it.results.isEmpty()) {
                            _listPopularState.value = ListMoviePageViewState.Empty
                        } else {
                            _listPopularState.value = ListMoviePageViewState.Success(it)
                        }
                    }
                }
        }
    }

    fun getAccount() {
        viewModelScope.launch {
            repository.getAccount()
                .catch { _ -> _account.value = AccountResponse() }
                .collect {
                    it?.let {
                        _account.value = it
                    }
                }
        }
    }
}