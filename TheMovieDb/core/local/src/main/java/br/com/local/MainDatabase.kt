package br.com.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.local.converters.ListIntConverter
import br.com.local.converters.ListStringConverter
import br.com.local.dao.TrendingMovieRemoteKeysDao
import br.com.local.dao.TrendingMoviesDao
import br.com.local.model.TrendingMovieEntity
import br.com.local.model.TrendingMovieRemoteKeyEntity

@Database(
    entities = [
        TrendingMovieEntity::class,
        TrendingMovieRemoteKeyEntity::class
    ],
    version = 1,
)
@TypeConverters(ListIntConverter::class, ListStringConverter::class)
abstract class MainDatabase : RoomDatabase() {
    abstract fun trendingMoviesDao(): TrendingMoviesDao
    abstract fun trendingMovieRemoteKeysDao(): TrendingMovieRemoteKeysDao
}