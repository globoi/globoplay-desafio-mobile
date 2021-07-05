package com.maslima.globo_play_recrutamento.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieDatabaseItem::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDatabaseDao
}