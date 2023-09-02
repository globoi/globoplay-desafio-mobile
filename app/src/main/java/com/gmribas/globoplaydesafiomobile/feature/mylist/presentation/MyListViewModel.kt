package com.gmribas.globoplaydesafiomobile.feature.mylist.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.gmribas.globoplaydesafiomobile.core.presentation.BaseViewModel
import com.gmribas.globoplaydesafiomobile.core.presentation.UiState
import com.gmribas.globoplaydesafiomobile.core.domain.model.MediaDetails
import com.gmribas.globoplaydesafiomobile.feature.mylist.domain.usecase.GetAllSavedMediaUseCase
import com.gmribas.globoplaydesafiomobile.feature.mylist.presentation.mapper.MediaListUIMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MyListViewModel(
    private val getAllSavedMediaUseCase: GetAllSavedMediaUseCase,
    private val mediaListUIMapper: MediaListUIMapper
): BaseViewModel<Any>() {

    private val _mediaDetailsList: MutableStateFlow<UiState<List<MediaDetails>>> by lazy {
        MutableStateFlow(UiState.Default)
    }

    val mediaDetailsList: StateFlow<UiState<List<MediaDetails>>> = _mediaDetailsList.asStateFlow()

    override fun onCreate(owner: LifecycleOwner) {
        getAllMedia()
    }

    private fun getAllMedia() {
        submitState(UiState.Loading)

        viewModelScope.launch {
            getAllSavedMediaUseCase
                .execute(GetAllSavedMediaUseCase.Request())
                .map { mediaListUIMapper.convert(it) }
                .collectLatest {
                    _mediaDetailsList.value = it
                }
        }
    }
}