package br.com.details_movie.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import br.com.details_movie.domain.model.Movie
import br.com.details_movie.domain.usecase.GetMovieUseCase
import br.com.favorites.domain.usecase.AddFavoriteMovieUseCase
import br.com.favorites.domain.usecase.GetIsFavoriteMovieUseCase
import br.com.favorites.domain.usecase.RemoveFavoriteUseCase
import br.com.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val getIsFavoriteMovieUseCase: GetIsFavoriteMovieUseCase,
    private val addFavoriteMovie: AddFavoriteMovieUseCase,
    private val removeFavorite: RemoveFavoriteUseCase

    )   : ViewModel() {

    private val _isMovieSaved = MutableLiveData<Boolean>()
    val isMovieSaved: LiveData<Boolean> = _isMovieSaved

    private val _uiState = MutableStateFlow<MovieUiState>(
        MovieUiState.Empty
    )

    val uiState = _uiState.asStateFlow()



    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            getMovieUseCase(movieId).collectLatest {resource ->

                    when(resource) {
                        is Resource.Success -> _uiState.value = MovieUiState.Success(resource.data)
                        is Resource.Error -> _uiState.value = MovieUiState.Empty
                        is Resource.Loading -> _uiState.value = MovieUiState.Empty
                    }
            }
        }
    }

    fun checkIfMovieIsSaved(movieId: Int) {
        viewModelScope.launch {
            getIsFavoriteMovieUseCase (movieId)
                .collect { isSaved ->
                    _isMovieSaved.value = isSaved
                }
        }
    }

    fun addMovieFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            addFavoriteMovie (movie.id)
        }
    }

    fun removeFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            removeFavorite.invoke(movie.id)
        }
    }

}