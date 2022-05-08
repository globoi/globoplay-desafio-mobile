package com.ftoniolo.core.usecase.base

sealed class ResultStatus <out T> {

    object Loading : ResultStatus<Nothing>()
    data class Success<out T>(val data:T) : ResultStatus<T>()
    data class Error(val throwable: Throwable) : ResultStatus<Nothing>()

    override fun toString(): String {
        return when (this) {
            Loading -> "Loading"
            is Success<*> -> "Sucess[data=$data]"
            is Error -> "Error[throwable=$throwable]"
        }
    }
}
