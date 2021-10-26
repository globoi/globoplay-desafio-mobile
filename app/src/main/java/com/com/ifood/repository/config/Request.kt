package com.com.ifood.repository.config

import com.com.ifood.extension.alternateThreadAndBackToMain
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import retrofit2.Response

fun <T> doRemoteRequest(
    callStorageRequest: () -> Observable<Response<T>>,
    successListener: (model: T?) -> Unit,
    failureListener: (exception: Throwable) -> Unit
): Disposable {

    return callStorageRequest()
        .alternateThreadAndBackToMain()
        .subscribe({
            if (it.isSuccessful) {
                successListener(it.body())
            } else {
                failureListener(
                    ResponseCodeException(
                        it.code()
                    )
                )
            }

        }) {
            failureListener(it)
        }
}

