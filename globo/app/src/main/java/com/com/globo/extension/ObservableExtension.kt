package com.com.globo.extension

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.alternateThreadAndBackToMain(scheduler: Scheduler = Schedulers.io()) =
    subscribeOn(scheduler)
        .observeOn(AndroidSchedulers.mainThread())
