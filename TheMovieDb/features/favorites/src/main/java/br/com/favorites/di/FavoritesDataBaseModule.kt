package br.com.favorites.di

import br.com.local.MainDatabase
import br.com.local.dao.favorities.FavoritiesMovieRemoteKeysDao
import br.com.local.dao.favorities.FavoritiesMoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FavoritesDataBaseModule {

    @Singleton
    @Provides
    fun provideFavoritesMoviesDao(database: MainDatabase) : FavoritiesMoviesDao = database.favoritiesMoviesDao()

    @Singleton
    @Provides
    fun providesFavoritesMovieRemoteKeysDao(database: MainDatabase) : FavoritiesMovieRemoteKeysDao = database.favoritiesMovieRemoteKeysDao()
}