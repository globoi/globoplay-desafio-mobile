package br.com.nerdrapido.themoviedbapp.data.model

import br.com.nerdrapido.themoviedbapp.data.model.error.ErrorResponse

/**
 * Created By FELIPE GUSBERTI @ 16/03/2020
 */
sealed class ResponseWrapper<out T> {
    data class Success<out T>(val value: T) : ResponseWrapper<T>()
    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null) :
        ResponseWrapper<Nothing>()

    object NetworkError : ResponseWrapper<Nothing>()
}