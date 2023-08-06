package com.reisdeveloper.data.di

import com.reisdeveloper.data.api.MovieApi
import com.reisdeveloper.data.api.RetrofitInstance
import com.reisdeveloper.data.repository.MovieRepository
import com.reisdeveloper.data.repository.ListsRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<MovieApi> { RetrofitInstance.create() }

    single<MovieRepository> { ListsRepositoryImpl(get()) }
}