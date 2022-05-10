package com.ftoniolo.globoplay.framework.di

import com.ftoniolo.core.usecase.GetPopularFilmsUseCase
import com.ftoniolo.core.usecase.GetPopularFilmsUseCaseImpl
import com.ftoniolo.core.usecase.GetFilmsByCategoryUseCase
import com.ftoniolo.core.usecase.GetFilmsByCategoryUseCaseImpl
import com.ftoniolo.core.usecase.GetWatchTooUseCase
import com.ftoniolo.core.usecase.GetWatchTooUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindPopularFilmsUseCase(useCase: GetPopularFilmsUseCaseImpl): GetPopularFilmsUseCase

    @Binds
    fun bindMovieByCategoryUseCase(useCase: GetFilmsByCategoryUseCaseImpl): GetFilmsByCategoryUseCase

    @Binds
    fun bindGetWatchTooUseCase(useCaseImpl: GetWatchTooUseCaseImpl): GetWatchTooUseCase
}