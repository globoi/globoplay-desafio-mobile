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