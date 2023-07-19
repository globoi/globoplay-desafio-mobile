package com.app.fakegloboplay.network

import com.app.fakegloboplay.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request().newBuilder()
            .addHeader("Authorization", BuildConfig.Token)
            .build()
        return chain.proceed(req)
    }
}