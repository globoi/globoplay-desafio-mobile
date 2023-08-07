package com.reisdeveloper.data.api

import com.reisdeveloper.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

object HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
            chain.proceed(
                    chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Accept", "application/json")
                            .addHeader("Authorization", BuildConfig.API_ACCESS_TOKEN)
                            .build())
}