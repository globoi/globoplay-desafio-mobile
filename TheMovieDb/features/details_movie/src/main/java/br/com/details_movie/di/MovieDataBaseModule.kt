package br.com.details_movie.di

import br.com.local.MainDatabase
import br.com.local.dao.movie_details.MovieDetailsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MovieDataBaseModule {

    @Singleton
    @Provides
    fun providesMoviesDao(database: MainDatabase) : MovieDetailsDao  = database.moviesDao()
}