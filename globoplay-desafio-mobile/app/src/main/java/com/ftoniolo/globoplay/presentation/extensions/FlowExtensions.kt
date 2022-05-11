package com.ftoniolo.globoplay.presentation.extensions

import com.ftoniolo.core.usecase.base.ResultStatus
import kotlinx.coroutines.flow.Flow

suspend fun <T> Flow<ResultStatus<T>>.watchStatus(
    loading: suspend () -> Unit = {},
    success: suspend (data: T) -> Unit,
    error: suspend (throwable : Throwable) -> Unit
) {
    collect{ status ->
        when(status) {
            ResultStatus.Loading -> loading.invoke()
            is ResultStatus.Success -> success(status.data)
            is ResultStatus.Error -> error(status.throwable)
        }
    }
}