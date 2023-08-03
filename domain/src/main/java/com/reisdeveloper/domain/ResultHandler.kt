package com.reisdeveloper.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetAddress
import java.net.UnknownHostException

fun <T> T.asSuccess(): ResultWrapper.Success<T> = ResultWrapper.Success(this)

suspend fun Throwable.asFailure(): ResultWrapper.Failure = when (this) {
    else -> {
        if (isNetworkConnected()) {
            ResultWrapper.Failure(Error.UnknownException(this))
        } else {
            ResultWrapper.Failure(Error.NetworkException(this))
        }
    }
}

suspend fun isNetworkConnected(): Boolean =
    try {
        withContext(Dispatchers.IO) {
            val address = InetAddress.getByName("www.google.com")
            address.hostName.isNotEmpty()
        }
    } catch (e: UnknownHostException) {
        false
    }