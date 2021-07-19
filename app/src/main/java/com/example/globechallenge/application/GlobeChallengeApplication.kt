package com.example.globechallenge.application

import android.app.Application
import com.example.globechallenge.data.repository.favorites.FavoriteMoviesRepository
import com.example.globechallenge.data.repository.favorites.FavoriteMoviesRoomDatabase

class GlobeChallengeApplication: Application() {

    //First create a database
    private val database by lazy {
        FavoriteMoviesRoomDatabase.getDatabase(this@GlobeChallengeApplication)
    }

    //After create a repository
    val repository by lazy {
        FavoriteMoviesRepository(database.favoriteMoviesDao())
    }
}