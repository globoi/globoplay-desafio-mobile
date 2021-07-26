package com.example.globechallenge.ui.home.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.globechallenge.R
import com.example.globechallenge.data.model.models.home.MovieToGenre
import com.example.globechallenge.data.repository.home.HomeRepositoryImplementation
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeViewModel(private val repositoryImplementation: HomeRepositoryImplementation) : ViewModel() {

    val movieByGenreMutableLiveData = MutableLiveData<List<MovieToGenre>>()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getMovieByGenre() {
        var result: List<MovieToGenre>? = null
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, exception -> }) {
            result = repositoryImplementation.getMovieByGenre()
        }.invokeOnCompletion { throwable ->
            if (throwable != null) {
                when (throwable) {
                    is HttpException -> {
                        when (throwable.code()) {
                            401 -> {
                                viewFlipperLiveData.postValue(Pair(VIEW_FLIPPER_ERROR, R.string.msg_error_401))
                            }
                            404 -> {
                                viewFlipperLiveData.postValue(Pair(VIEW_FLIPPER_ERROR, R.string.msg_error_404))
                            }
                            else -> {
                                viewFlipperLiveData.postValue(Pair(VIEW_FLIPPER_ERROR, R.string.msg_generic_error))
                            }
                        }
                    }
                    else -> {
                        viewFlipperLiveData.postValue(Pair(VIEW_FLIPPER_ERROR, R.string.msg_generic_error))
                    }
                }
            } else {
                movieByGenreMutableLiveData.postValue(result!!)
                viewFlipperLiveData.postValue(Pair(VIEW_FLIPPER_RECYCLER, null))
            }
        }
    }

    class HomeViewModelFactory(private val repositoryImplementation: HomeRepositoryImplementation) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repositoryImplementation) as T
        }
    }

    companion object {
        private const val VIEW_FLIPPER_RECYCLER = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}