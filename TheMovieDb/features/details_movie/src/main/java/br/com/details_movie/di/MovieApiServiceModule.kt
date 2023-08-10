package br.com.details_movie.di

import br.com.details_movie.data.remote.MovieApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
object MovieApiServiceModule {
@ViewModelScoped
@Provides
fun provideMovieApiService(retrofit: Retrofit) :MovieApiService =retrofit.create(MovieApiService::class.java)
}