package com.nroncari.movieplay.data.localdatasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nroncari.movieplay.data.localdatasource.dao.MovieDetailDAO
import com.nroncari.movieplay.data.model.MovieDTO

@Database(
    version = 1,
    entities = [MovieDTO::class],
    exportSchema = false
)
abstract class ConnectionDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDetailDAO
}