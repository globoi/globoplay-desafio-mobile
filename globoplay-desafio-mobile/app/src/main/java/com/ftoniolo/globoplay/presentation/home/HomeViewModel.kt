package com.ftoniolo.globoplay.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.core.usecase.GetFilmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFilmsUseCase: GetFilmsUseCase
) : ViewModel() {
        fun filmsPagingData(): Flow<PagingData<Film>> {
            return getFilmsUseCase(
                GetFilmsUseCase.GetFilmsParams(getPageConfig())
            ).cachedIn(viewModelScope)
        }

    private fun getPageConfig() = PagingConfig(
            pageSize = 20
    )
}