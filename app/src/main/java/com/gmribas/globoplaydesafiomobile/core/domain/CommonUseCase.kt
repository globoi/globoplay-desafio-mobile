package com.gmribas.globoplaydesafiomobile.core.domain

import com.gmribas.globoplaydesafiomobile.core.exception.UseCaseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import timber.log.Timber

abstract class CommonUseCase<RQ: CommonUseCase.Request, RP : CommonUseCase.Response>() {

    suspend fun execute(request: RQ) = process(request)
        .map {
            it.hashCode()
            @Suppress("USELESS_CAST")
            ResultUseCase.Success(it) as ResultUseCase<RP>
        }
        .flowOn(Dispatchers.IO)
        .catch {
            Timber.e(it)
            emit(ResultUseCase.Error(UseCaseException.createFromThrowable(it)))
        }

    internal abstract suspend fun process(request: RQ): Flow<RP>

    interface Request

    interface Response
}
