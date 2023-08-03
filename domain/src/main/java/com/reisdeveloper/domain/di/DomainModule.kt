package com.reisdeveloper.domain.di

import com.reisdeveloper.domain.usecases.GetFavoriteMoviesUseCase
import com.reisdeveloper.domain.usecases.GetListUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetListUseCase(get()) }
    single { GetFavoriteMoviesUseCase(get()) }
}