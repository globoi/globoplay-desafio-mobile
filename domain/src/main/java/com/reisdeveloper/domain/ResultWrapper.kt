package com.reisdeveloper.domain

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Failure(val error: Error) : ResultWrapper<Nothing>()
    object Loading : ResultWrapper<Nothing>()
    object DismissLoading : ResultWrapper<Nothing>()
}

sealed class Error {
    data class NetworkException(val cause: Throwable?) : Error()
    data class UnknownException(val cause: Throwable?) : Error()
}