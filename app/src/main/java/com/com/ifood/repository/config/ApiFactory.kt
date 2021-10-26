package com.com.ifood.repository.config

inline fun <reified T> createApi(): T {
    return provideRetrofit().create(T::class.java)
}