package br.com.details_movie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import br.com.details_movie.domain.model.Movie
import br.com.details_movie.domain.usecase.GetMovieUseCase
import br.com.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,

    )   : ViewModel() {

    private val _uiState = MutableStateFlow<MovieUiState>(
        MovieUiState.Empty
    )

    val uiState = _uiState.asStateFlow()
//
//    private val _stateMovies = MutableStateFlow<Resource<Movie>>(Resource.Loading)
//
//    val uiState = _stateMovies.asStateFlow()


    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            getMovieUseCase(movieId).collectLatest {resource ->

                    when(resource) {
                        is Resource.Success -> _uiState.value = MovieUiState.Success(resource.data)
                        is Resource.Error -> _uiState.value = MovieUiState.Empty
                        is Resource.Loading -> _uiState.value = MovieUiState.Empty
                    }
//                _stateMovies.value = it
            }
        }
    }

}