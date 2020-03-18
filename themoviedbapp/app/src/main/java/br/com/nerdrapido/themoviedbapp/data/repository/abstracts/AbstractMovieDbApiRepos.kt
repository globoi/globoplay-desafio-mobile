package br.com.nerdrapido.themoviedbapp.data.repository.abstracts

import br.com.nerdrapido.themoviedbapp.data.model.ResponseWrapper
import br.com.nerdrapido.themoviedbapp.data.model.error.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import timber.log.Timber
import java.io.IOException

/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
abstract class AbstractMovieDbApiRepos(
    protected val retrofit: Retrofit
) {

    protected val dispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): ResponseWrapper<T> {
        return withContext(dispatcher) {
            try {
                ResponseWrapper.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                Timber.e(throwable)
                when (throwable) {
                    is IOException -> ResponseWrapper.NetworkError
                    is HttpException -> {
                        val code = throwable.code()
                        val errorResponse = convertErrorBody(throwable)
                        ResponseWrapper.GenericError(code, errorResponse)
                    }
                    else -> {
                        ResponseWrapper.GenericError(null, null)
                    }
                }
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
        return try {
            throwable.response()?.errorBody()?.source()?.let {
                Gson().fromJson(it.toString(), ErrorResponse::class.java)
            }
        } catch (exception: Exception) {
            null
        }
    }

}