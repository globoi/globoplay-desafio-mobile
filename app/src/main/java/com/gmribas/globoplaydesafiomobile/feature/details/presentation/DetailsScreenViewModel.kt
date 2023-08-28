package com.gmribas.globoplaydesafiomobile.feature.details.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.gmribas.globoplaydesafiomobile.core.presentation.BaseViewModel
import com.gmribas.globoplaydesafiomobile.core.presentation.UiState
import com.gmribas.globoplaydesafiomobile.feature.details.domain.model.MovieDetails
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetMovieDetailsUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.MovieDetailsUIMapper
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DetailsScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: GetMovieDetailsUseCase,
    private val mapper: MovieDetailsUIMapper
    ): BaseViewModel<MovieDetails>() {

//    private val _movieDetails: MutableState<UiState<MovieDetails>> by lazy {
//        mutableStateOf(UiState.Default)
//    }
//
//    val movieDetails: State<UiState<MovieDetails>> = _movieDetails

    override fun onCreate(owner: LifecycleOwner) {
        submitState(UiState.Loading)

        savedStateHandle.get<Int>("id")?.let {
            getMovieDetails(it)
        }
    }

    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            useCase
                .execute(GetMovieDetailsUseCase.Request(movieId))
                .map { mapper.convert(it) }
                .collectLatest {
                    submitState(it)
                }
        }
    }
}