package br.com.network



sealed class Resource<out T> {

    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val exception: NetworkException) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
