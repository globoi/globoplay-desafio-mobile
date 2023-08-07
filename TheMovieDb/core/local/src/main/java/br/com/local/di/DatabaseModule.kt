package br.com.local.di

import android.content.Context
import androidx.room.Room
import br.com.local.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


private const val DB_NAME = "themovie_db"


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideMainDataBase(@ApplicationContext appContext: Context): MainDatabase =
        Room.databaseBuilder(appContext, MainDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
}
