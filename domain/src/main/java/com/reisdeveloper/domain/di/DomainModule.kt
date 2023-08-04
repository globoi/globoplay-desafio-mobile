package com.reisdeveloper.domain.di

import com.reisdeveloper.domain.usecases.FavoriteMovieUseCase
import com.reisdeveloper.domain.usecases.GetFavoriteMoviesUseCase
import com.reisdeveloper.domain.usecases.GetListUseCase
import com.reisdeveloper.domain.usecases.GetMovieDetailsUseCase
import com.reisdeveloper.domain.usecases.GetSimilarMoviesUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetListUseCase(get()) }
    single { GetFavoriteMoviesUseCase(get()) }
    single { GetSimilarMoviesUseCase(get()) }
    single { GetMovieDetailsUseCase(get()) }
    single { FavoriteMovieUseCase(get()) }
}