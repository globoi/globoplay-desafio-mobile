package com.ftoniolo.globoplay.framework.di

import com.ftoniolo.core.data.repository.FilmsRemoteDataSource
import com.ftoniolo.core.data.repository.FilmsRepository
import com.ftoniolo.globoplay.framework.FilmsRepositoryImpl
import com.ftoniolo.globoplay.framework.remote.RetrofitFilmsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FilmsRepositoryModule {

    @Binds
    fun bindFilmRepository(repository: FilmsRepositoryImpl): FilmsRepository

    @Binds
    fun bindRemoteDataSource(
        dataSource: RetrofitFilmsDataSource
    ) : FilmsRemoteDataSource
}