package com.ftoniolo.globoplay.framework.di

import com.ftoniolo.core.usecase.base.AppCoroutinesDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesModule {

    @Provides
    fun provideDispatchers() = AppCoroutinesDispatchers(
        Dispatchers.IO,
        Dispatchers.Default,
        Dispatchers.Main
    )
}