package com.maslima.globo_play_recrutamento.di

import com.maslima.globo_play_recrutamento.network.MovieService
import com.maslima.globo_play_recrutamento.network.model.ImageConfigDtoMapper
import com.maslima.globo_play_recrutamento.network.model.MovieDtoMapper
import com.maslima.globo_play_recrutamento.repository.ConfigRepository
import com.maslima.globo_play_recrutamento.repository.ConfigRepositoryImpl
import com.maslima.globo_play_recrutamento.repository.MovieRepository
import com.maslima.globo_play_recrutamento.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMovieRepository(
        movieService: MovieService,
        movieDtoMapper: MovieDtoMapper
    ): MovieRepository {
        return MovieRepositoryImpl(
            movieService,
            movieDtoMapper
        )
    }

    @Singleton
    @Provides
    fun provideConfigRepository(
        configService: MovieService,
        imageConfigDtoMapper: ImageConfigDtoMapper
    ): ConfigRepository {
        return ConfigRepositoryImpl(configService, imageConfigDtoMapper)
    }
}