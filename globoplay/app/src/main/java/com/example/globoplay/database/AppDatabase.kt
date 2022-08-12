package com.example.globoplay.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.globoplay.database.daos.MyListDao
import com.example.globoplay.database.models.MyList


@Database(entities = [MyList::class], version = 2)
abstract class AppDatabase:RoomDatabase() {
    abstract fun myListDao(): MyListDao

    companion object {

        private const val DATABASE_NAME: String = "MyListMedia"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, DATABASE_NAME
            ).fallbackToDestructiveMigration()
                .build()
    }
}