package com.maslima.globo_play_recrutamento.di

import android.content.Context
import androidx.room.Room
import com.maslima.globo_play_recrutamento.database.MovieDatabase
import com.maslima.globo_play_recrutamento.database.MovieDatabaseDao
import com.maslima.globo_play_recrutamento.database.MovieDatabaseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideChannelDao(appDatabase: MovieDatabase): MovieDatabaseDao {
        return appDatabase.movieDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MovieDatabase {
        return Room.databaseBuilder(
            appContext,
            MovieDatabase::class.java,
            "MovieDatabase"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMovieMapper(): MovieDatabaseMapper {
        return MovieDatabaseMapper()
    }
}