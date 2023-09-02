package com.gmribas.globoplaydesafiomobile.core.domain

import com.gmribas.globoplaydesafiomobile.core.exception.UseCaseException


sealed class ResultUseCase<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultUseCase<T>()
    class Error(val exception: UseCaseException) : ResultUseCase<Nothing>()
}
