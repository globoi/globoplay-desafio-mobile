package com.mazer.globoplayapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mazer.globoplayapp.data.local.dao.MovieDao
import com.mazer.globoplayapp.domain.entities.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}