package com.tiagopereira.globotmdb.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tiagopereira.globotmdb.data.ApiResponse
import com.tiagopereira.globotmdb.utils.Constants.Companion.POPULAR
import com.tiagopereira.globotmdb.utils.NetworkUtils
import com.tiagopereira.globotmdb.viewmodel.repository.MainRepository

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    private val _statusNetwork = MutableLiveData<Boolean>()
    val statusNetwork: LiveData<Boolean> = _statusNetwork

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    private val _movies = currentQuery.switchMap { queryString ->
        repository.getFilterResults(queryString).cachedIn(viewModelScope)
    }
    val movies: LiveData<PagingData<ApiResponse.Result>> = _movies

    fun checkNetwork(context: Context) {
        _statusNetwork.postValue(NetworkUtils.isNetworkAvailable(context))
    }

    fun searchMovie(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = POPULAR
    }
}