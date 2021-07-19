package com.example.globechallenge.data.repository.favorites

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.globechallenge.data.model.entities.FavoriteMoviesEntity

@Database(entities = [FavoriteMoviesEntity::class], version = 2, exportSchema = false)
abstract class FavoriteMoviesRoomDatabase : RoomDatabase() {

    abstract fun favoriteMoviesDao(): FavoriteMoviesDao

    companion object {
        @Volatile //meaning that writes to this field are immediately made visible to other threads.
        private var INSTANCE: FavoriteMoviesRoomDatabase? = null

        fun getDatabase(context: Context): FavoriteMoviesRoomDatabase {
            //If the INSTANCE is not null, then return it,
            //if it is, them create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteMoviesRoomDatabase::class.java,
                    "favorite_movies_table"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}