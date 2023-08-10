package br.com.favorites.di

import br.com.favorites.data.remote.FavoriteApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FavoriteServiceModule {
    @Provides
    @Singleton
    fun providesCharacterService(
        retrofit: Retrofit
    ): FavoriteApiService = retrofit.create(FavoriteApiService::class.java)
}