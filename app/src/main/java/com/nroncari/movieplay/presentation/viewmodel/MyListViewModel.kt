package com.nroncari.movieplay.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.nroncari.movieplay.domain.usecase.ListAllMovieDatabaseUseCase

class MyListViewModel(
    private val listAllUseCase: ListAllMovieDatabaseUseCase,
) : ViewModel() {

    fun listAll() = listAllUseCase()
}