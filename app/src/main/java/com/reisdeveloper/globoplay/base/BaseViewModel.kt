package com.reisdeveloper.globoplay.base

import androidx.lifecycle.ViewModel
import com.reisdeveloper.domain.OnCompletionBaseViewModel
import com.reisdeveloper.domain.OnErrorBaseViewModel
import com.reisdeveloper.domain.OnLoadingBaseViewModel
import com.reisdeveloper.domain.OnSuccessBaseViewModel
import com.reisdeveloper.domain.ResultWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    open fun <T : Any> Flow<ResultWrapper<T>>.singleExec(
        onError: OnErrorBaseViewModel? = null,
        onSuccessBaseViewModel: OnSuccessBaseViewModel<T>? = null,
        onCompletionBaseViewModel: OnCompletionBaseViewModel? = null,
        onLoadingBaseViewModel: OnLoadingBaseViewModel? = null
    ) = onEach {
        when (val result = it) {
            is ResultWrapper.Loading -> {
                onLoadingBaseViewModel?.invoke(true)
            }
            is ResultWrapper.Failure -> {
                onError?.invoke(result.error)
            }
            is ResultWrapper.Success<T> ->
                onSuccessBaseViewModel?.invoke(result.value)
            is ResultWrapper.DismissLoading -> {
                onLoadingBaseViewModel?.invoke(false)
            }
        }
    }.onCompletion {
        onCompletionBaseViewModel?.invoke()
    }.catch {
        onLoadingBaseViewModel?.invoke(false)
    }.launchIn(CoroutineScope(coroutineContext))

}