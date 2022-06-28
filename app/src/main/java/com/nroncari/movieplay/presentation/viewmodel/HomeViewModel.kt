package com.nroncari.movieplay.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nroncari.movieplay.data.retrofit.ErrorMessagesConst.CONNECT_EXCEPTION_ERROR
import com.nroncari.movieplay.data.retrofit.ErrorMessagesConst.NULL_VALUE_ERROR
import com.nroncari.movieplay.data.retrofit.ErrorMessagesConst.REQUISITION_CODE
import com.nroncari.movieplay.data.retrofit.ErrorMessagesConst.TIMEOUT_ERROR
import com.nroncari.movieplay.data.retrofit.ErrorMessagesConst.UNEXPECTED_ERROR
import com.nroncari.movieplay.domain.usecase.MovieUseCase
import com.nroncari.movieplay.presentation.model.MovieListItemPresentation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class HomeViewModel(
    private val getMoviesByGenre: MovieUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _onRequisitionError = MutableLiveData<String?>()
    val onRequisitionError: LiveData<String?> get() = _onRequisitionError

    private val _movies = MutableLiveData<List<MovieListItemPresentation>>()
    val movies: LiveData<List<MovieListItemPresentation>> get() = _movies

    fun getMoviesByGenre() {
        viewModelScope.launch(dispatcher) {

            kotlin.runCatching {
                getMoviesByGenre(1, 28)
            }.onSuccess { movies ->
                _movies.postValue(movies)
            }.onFailure { error ->
                onError(error)
            }
        }
    }

    private fun onError(error: Throwable) {
        val messageError: String = when (error) {
            is HttpException -> "$REQUISITION_CODE ${error.code()}, ${
                error.response()!!.errorBody()!!.string()
            }"
            is UnknownHostException -> CONNECT_EXCEPTION_ERROR
            is SocketTimeoutException -> TIMEOUT_ERROR
            is ConnectException -> CONNECT_EXCEPTION_ERROR
            is IllegalArgumentException -> NULL_VALUE_ERROR
            else -> UNEXPECTED_ERROR
        }
        _onRequisitionError.postValue(messageError)
    }
}