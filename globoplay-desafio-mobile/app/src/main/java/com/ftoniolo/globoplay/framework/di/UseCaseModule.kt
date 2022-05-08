package com.ftoniolo.globoplay.framework.di

import com.ftoniolo.core.usecase.GetFilmsUseCase
import com.ftoniolo.core.usecase.GetFilmsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindFilmsUseCase(useCase: GetFilmsUseCaseImpl): GetFilmsUseCase
}