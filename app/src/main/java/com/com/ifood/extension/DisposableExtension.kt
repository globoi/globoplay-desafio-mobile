package com.com.ifood.extension

import io.reactivex.disposables.Disposable

fun Disposable?.onCleared() {
    this?.dispose()
}