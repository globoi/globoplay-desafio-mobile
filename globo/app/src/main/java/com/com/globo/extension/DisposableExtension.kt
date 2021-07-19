package com.com.globo.extension

import io.reactivex.disposables.Disposable

fun Disposable?.onCleared() {
    this?.dispose()
}