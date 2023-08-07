package br.com.network

import com.google.gson.Gson
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ResultCall<T>(private val delegate: Call<T>) : Call<Result<T>> {

    override fun clone(): Call<Result<T>> = ResultCall(delegate.clone())

    override fun execute(): Response<Result<T>> = Response.success(
        Result.success(delegate.execute().body()!!)
    )

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() {
        delegate.cancel()
    }

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    override fun enqueue(callback: Callback<Result<T>>) {
        delegate.enqueue(
            object : Callback<T> {
                override fun onResponse(call: Call<T>,
                                        response: Response<T>) {
                    callback.onResponse(
                        this@ResultCall,
                        if(response.isSuccessful) {
                            Response.success(
                                response.code(),
                                Result.success(response.body()!!)
                            )
                        } else {
                            Response.success(
                                Result.failure(
                                    runCatching {
                                        Gson().fromJson(
                                            response.errorBody()?.string(),
                                            NetworkException::class.java
                                        )
                                    }.getOrDefault(
                                        NetworkException(code = NetworkException.CODE_PARSING_EXCEPTION)
                                    )
                                )
                            )
                        }
                    )
                }

                override fun onFailure(call: Call<T>, throwable: Throwable) {
                    callback.onResponse(
                        this@ResultCall,
                        Response.success(
                            Result.failure(NetworkException.getFromThrowable(throwable))
                        )
                    )
                }

            }
        )
    }
}