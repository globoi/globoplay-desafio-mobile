package com.nroncari.movieplay.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.nroncari.movieplay.data.retrofit.ErrorMessagesConst.CONNECT_EXCEPTION_ERROR
import com.nroncari.movieplay.data.retrofit.ErrorMessagesConst.NULL_VALUE_ERROR
import com.nroncari.movieplay.data.retrofit.ErrorMessagesConst.REQUISITION_CODE
import com.nroncari.movieplay.data.retrofit.ErrorMessagesConst.TIMEOUT_ERROR
import com.nroncari.movieplay.data.retrofit.ErrorMessagesConst.UNEXPECTED_ERROR
import com.nroncari.movieplay.domain.mapper.MovieToPresentationMapper
import com.nroncari.movieplay.domain.model.MovieListItemDomain
import com.nroncari.movieplay.domain.usecase.*
import com.nroncari.movieplay.presentation.model.MovieListItemPresentation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class HomeViewModel(
    private val getActionMovies: GetActionMoviesUseCase,
    private val getAnimationMovies: GetAnimationMoviesUseCase,
    private val getComedyMovies: GetComedyMoviesUseCase,
    private val getDramaMovies: GetDramaMoviesUseCase,
    private val getHorrorMovies: GetHorrorMoviesUseCase,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _onRequisitionError = MutableLiveData<String?>()
    val onRequisitionError: LiveData<String?> get() = _onRequisitionError

    private val _actionMovies = MutableLiveData<PagingData<MovieListItemPresentation>>()
    val actionMovies: LiveData<PagingData<MovieListItemPresentation>> get() = _actionMovies

    private val _animationMovies = MutableLiveData<PagingData<MovieListItemPresentation>>()
    val animationMovies: LiveData<PagingData<MovieListItemPresentation>> get() = _animationMovies

    private val _comedyMovies = MutableLiveData<PagingData<MovieListItemPresentation>>()
    val comedyMovies: LiveData<PagingData<MovieListItemPresentation>> get() = _comedyMovies

    private val _dramaMovies = MutableLiveData<PagingData<MovieListItemPresentation>>()
    val dramaMovies: LiveData<PagingData<MovieListItemPresentation>> get() = _dramaMovies

    private val _horrorMovies = MutableLiveData<PagingData<MovieListItemPresentation>>()
    val horrorMovies: LiveData<PagingData<MovieListItemPresentation>> get() = _horrorMovies

    init {
        launchGetMovies(dispatcher, _actionMovies) { getActionMovies() }
        launchGetMovies(dispatcher, _animationMovies) { getAnimationMovies() }
        launchGetMovies(dispatcher, _comedyMovies) { getComedyMovies() }
        launchGetMovies(dispatcher, _dramaMovies) { getDramaMovies() }
        launchGetMovies(dispatcher, _horrorMovies) { getHorrorMovies() }
    }

    private fun launchGetMovies(
        dispatcher: CoroutineDispatcher,
        field: MutableLiveData<PagingData<MovieListItemPresentation>>,
        function: () -> Flow<PagingData<MovieListItemDomain>>
    ) {
        viewModelScope.launch(dispatcher) {
            function().collect { movies ->
                field.postValue(movies.map { MovieToPresentationMapper().map(it) })
            }
        }
    }
}