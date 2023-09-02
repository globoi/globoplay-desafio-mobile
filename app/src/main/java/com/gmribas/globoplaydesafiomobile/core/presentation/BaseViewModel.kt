package com.gmribas.globoplaydesafiomobile.core.presentation

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import com.gmribas.globoplaydesafiomobile.core.domain.ResultUseCase
import com.gmribas.globoplaydesafiomobile.core.exception.UseCaseException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<T : Any> : ViewModel(), DefaultLifecycleObserver {

    protected val errorHandler = CoroutineExceptionHandler { _, exception ->
        ResultUseCase.Error(UseCaseException.createFromThrowable(exception))
        submitState(UiState.Error())
    }

    private val _viewState: MutableStateFlow<UiState<T>> by lazy {
        MutableStateFlow(setInitialState())
    }

    val viewState: StateFlow<UiState<T>> = _viewState.asStateFlow()

    private val initialState: UiState<T> by lazy { setInitialState() }

    private fun setInitialState() = UiState.Default

    protected fun submitState(state: UiState<T>) {
        _viewState.value = state
    }
}
