package com.ftoniolo.globoplay.framework.di

import com.ftoniolo.core.data.repository.FavoritesLocalDataSource
import com.ftoniolo.core.data.repository.FavoritesRepository
import com.ftoniolo.core.data.repository.FilmsRemoteDataSource
import com.ftoniolo.core.data.repository.FilmsRepository
import com.ftoniolo.globoplay.framework.FavoritesRepositoryImpl
import com.ftoniolo.globoplay.framework.FilmsRepositoryImpl
import com.ftoniolo.globoplay.framework.local.RoomFavoritesDataSource
import com.ftoniolo.globoplay.framework.remote.RetrofitFilmsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FavoritesRepositoryModule {

    @Binds
    fun bindFavoritesRepository(repository: FavoritesRepositoryImpl): FavoritesRepository

    @Binds
    fun bindLocalDataSource(
        dataSource: RoomFavoritesDataSource
    ) : FavoritesLocalDataSource
}