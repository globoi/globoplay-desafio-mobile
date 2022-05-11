package com.ftoniolo.globoplay.presentation.home

import androidx.lifecycle.ViewModel
import com.ftoniolo.core.usecase.GetFilmsByCategoryUseCase
import com.ftoniolo.core.usecase.base.CoroutinesDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFilmsByCategoryUseCase: GetFilmsByCategoryUseCase,
    coroutinesDispatchers: CoroutinesDispatchers,

) : ViewModel() {

    val categories = HomeUiActionStateLiveData(
        coroutinesDispatchers.main(),
        getFilmsByCategoryUseCase
    )
}