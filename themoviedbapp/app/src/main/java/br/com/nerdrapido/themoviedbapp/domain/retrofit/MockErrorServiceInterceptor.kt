package br.com.nerdrapido.themoviedbapp.domain.retrofit

import br.com.nerdrapido.themoviedbapp.data.model.error.ErrorResponse
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody


/**
 * Created By FELIPE GUSBERTI @ 16/03/2020
 */
class MockErrorServiceInterceptor(private val responseBodyObject: ErrorResponse? = null) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val responseBodyObject = this.responseBodyObject ?: ErrorResponse(
            7,
            "Invalid API key: You must be granted a valid key."
        )

        val responseBodyString = Gson().toJson(responseBodyObject)

        return Response.Builder()
            .code(400)
            .message(Gson().toJson(responseBodyString))
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(
                responseBodyString
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            .addHeader("content-type", "application/json")
            .build()
    }
}