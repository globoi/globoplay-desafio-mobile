package com.gmribas.globoplaydesafiomobile.core.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gmribas.globoplaydesafiomobile.core.data.database.dao.MediaDAO
import com.gmribas.globoplaydesafiomobile.core.data.database.entity.MediaDetailsEntity

@Database(entities = [(MediaDetailsEntity::class)], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun mediaDAO(): MediaDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                        .databaseBuilder(context.applicationContext, AppDatabase::class.java, "desafio_dabase.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE!!
            }
        }
    }
}