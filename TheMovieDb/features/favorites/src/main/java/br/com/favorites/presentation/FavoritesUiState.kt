package br.com.favorites.presentation

import androidx.paging.PagingData
import br.com.common.domain.model.Movie


sealed class FavoritesUiState {

    data class Success(
        val pagingData: PagingData<Movie>,
    ) : FavoritesUiState()
}