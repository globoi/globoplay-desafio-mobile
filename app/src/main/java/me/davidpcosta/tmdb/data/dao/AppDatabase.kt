package me.davidpcosta.tmdb.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import me.davidpcosta.tmdb.data.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
