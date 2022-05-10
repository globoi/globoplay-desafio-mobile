package com.ftoniolo.globoplay.presentation.details.watchtoo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ftoniolo.core.domain.model.WatchToo
import com.ftoniolo.core.usecase.GetWatchTooUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class WatchTooViewModel @Inject constructor(
    private val getWatchTooUseCase: GetWatchTooUseCase,
) : ViewModel() {

    fun watchTooPagingData(filmId: Long): Flow<PagingData<WatchToo>> {
        return getWatchTooUseCase(
            GetWatchTooUseCase.GetWatchTooParams(filmId, getPageConfig())
        ).cachedIn(viewModelScope)
    }

    private fun getPageConfig() = PagingConfig(
        pageSize = 20
    )
}

//    private val _uiState = MutableLiveData<UiState>()
//    val uiState: LiveData<UiState> get() = _uiState
//
//    fun getWatchToo(filmId: Long) = viewModelScope.launch {
//        getWatchTooUseCase.invoke(GetWatchTooUseCase.GetWatchTooParams(filmId))
//            .observeStatus()
//    }
//
//    private fun Flow<ResultStatus<List<WatchToo>>>.observeStatus() = viewModelScope.launch {
//        collect { status ->
//            _uiState.value = when (status) {
//                ResultStatus.Loading -> UiState.Loading
//                is ResultStatus.Success -> UiState.Success(status.data)
//                is ResultStatus.Error -> UiState.Error
//            }
//        }
//    }
//
//    sealed class UiState {
//        object Loading : UiState()
//        data class Success(val watchToo: List<WatchToo>) : UiState()
//        object Error : UiState()
//    }
