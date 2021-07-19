package com.example.globechallenge.data.repository.Favorities

import androidx.annotation.WorkerThread
import com.example.globechallenge.data.model.entities.FavoriteMoviesEntity

/* Declares the DAO as a private property in the constructor. Pass in the DAO
instead of the whole database, because you only need access to the DAO */
class FavoriteMoviesRepository(private val favoriteMoviesDao: FavoriteMoviesDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allFavoriteMovies = favoriteMoviesDao.getAllFavoriteMovies()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(favoriteMoviesEntity: FavoriteMoviesEntity) {
        favoriteMoviesDao.insert(favoriteMoviesEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteOneFavoriteMovie() {
        favoriteMoviesDao.deleteOneFavoriteMovie()
    }
}
