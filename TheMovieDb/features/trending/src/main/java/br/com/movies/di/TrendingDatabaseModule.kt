package br.com.movies.di


import br.com.local.MainDatabase
import br.com.local.dao.TrendingMovieRemoteKeysDao
import br.com.local.dao.TrendingMoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class TrendingDatabaseModule {
    @Singleton
    @Provides
    fun provideTrendingMoviesDao(database: MainDatabase): TrendingMoviesDao = database.trendingMoviesDao()

    @Singleton
    @Provides
    fun provideTrendingMovieRemoteKeysDao(database: MainDatabase): TrendingMovieRemoteKeysDao =
        database.trendingMovieRemoteKeysDao()
}