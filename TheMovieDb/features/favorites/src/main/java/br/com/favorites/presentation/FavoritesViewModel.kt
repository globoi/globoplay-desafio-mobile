package br.com.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.favorites.domain.usecase.GetFavoritesMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel
@Inject constructor(private val getFavoritesMoviesUseCase: GetFavoritesMoviesUseCase)
    : ViewModel() {

        private val _uiState = MutableStateFlow<br.com.favorites.presentation.FavoritesUiState>(
            FavoritesUiState.Success(PagingData.empty())
        )

    val uiState = _uiState.asStateFlow()
    init {
        getFavorites()
    }
    @OptIn(ExperimentalPagingApi::class)
    private fun getFavorites() {
        viewModelScope.launch {
            getFavoritesMoviesUseCase().cachedIn(viewModelScope)
                .collectLatest {paggindData ->

                _uiState.value = FavoritesUiState.Success(paggindData)
            }
        }
    }
}
