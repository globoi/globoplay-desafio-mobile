package com.example.globechallenge.application

import android.app.Application
import com.example.globechallenge.data.repository.Favorities.FavoriteMoviesRepository
import com.example.globechallenge.data.repository.Favorities.FavoriteMoviesRoomDatabase

class GloboChallengeApplication: Application() {

    //First create a database
    private val database by lazy {
        FavoriteMoviesRoomDatabase.getDatabase(this@GloboChallengeApplication)
    }

    //After create a repository
    val repository by lazy {
        FavoriteMoviesRepository(database.favoriteMoviesDao())
    }
}