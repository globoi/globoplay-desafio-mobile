package me.davidpcosta.tmdb.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import me.davidpcosta.tmdb.data.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAll(): List<Movie>

    @Query("SELECT * FROM movie WHERE id = :id ")
    fun findById(id: Long): Movie

    @Insert
    fun insertAll(vararg movies: Movie)

    @Insert
    fun insert(movie: Movie)

    @Delete
    fun delete(movie: Movie)
}