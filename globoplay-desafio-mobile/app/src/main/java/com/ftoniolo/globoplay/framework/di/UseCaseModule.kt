package com.ftoniolo.globoplay.framework.di

import com.ftoniolo.core.usecase.GetFilmsUseCase
import com.ftoniolo.core.usecase.GetFilmsUseCaseImpl
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
    fun bindGetFilmsUseCase(useCase: GetFilmsUseCaseImpl): GetFilmsUseCase

    @Binds
    fun bindGetWatchTooUseCase(useCaseImpl: GetWatchTooUseCaseImpl): GetWatchTooUseCase
}