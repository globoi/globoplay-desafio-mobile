package com.ftoniolo.globoplay.framework.di

import android.content.Context
import androidx.room.Room
import com.ftoniolo.core.data.DbConstants
import com.ftoniolo.globoplay.framework.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DbConstants.APP_DATABASE_NAME
    ).build()

    @Provides
    fun providerFavoriteDao(appDatabase: AppDatabase) = appDatabase.favoriteDao()
}