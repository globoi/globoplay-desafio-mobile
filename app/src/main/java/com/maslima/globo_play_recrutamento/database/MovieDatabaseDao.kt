package com.maslima.globo_play_recrutamento.database

import androidx.compose.runtime.MutableState
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.maslima.globo_play_recrutamento.domain.model.Movie

@Dao
interface MovieDatabaseDao {
    @Query("SELECT * FROM movie")
    fun getAll(): List<MovieDatabaseItem>

    @Query("SELECT * from movie where id = :id")
    fun getById(id: Int): MovieDatabaseItem

    @Insert
    suspend fun insert(movieDatabaseItem: MovieDatabaseItem)

    @Delete
    suspend fun delete(movieDatabaseItem: MovieDatabaseItem)
}