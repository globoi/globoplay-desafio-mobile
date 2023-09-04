package com.mazer.globoplayapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mazer.globoplayapp.domain.entities.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM favorites")
    fun getMyFavoritesMovies(): LiveData<List<Movie>>

    @Insert
    suspend fun saveFavoriteMovie(movie: Movie)

    @Delete
    suspend fun deleteFavoriteMovie(movie: Movie)

    @Query("SELECT * FROM favorites WHERE id = :id")
    fun getFavoriteMovie(id: Int): Movie?
}