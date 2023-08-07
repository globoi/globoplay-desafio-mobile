package br.com.movies.di

import br.com.movies.data.remote.TrendingApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Provides
    @Singleton
    fun providesCharacterService(
        retrofit: Retrofit
    ): TrendingApiService = retrofit.create(TrendingApiService::class.java)
}