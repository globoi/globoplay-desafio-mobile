package com.tiagopereira.globotmdb.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tiagopereira.globotmdb.database.entities.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM movie WHERE id = :idMovie")
    suspend fun getMovieById(idMovie: Int): Movie?

    @Query("DELETE FROM movie WHERE id = :idMovie")
    suspend fun removeById(idMovie: Int)

    @Query("SELECT * FROM movie LIMIT :init,:max")
    suspend fun getMoviePagination(init: Int, max: Int): List<Movie>

    @Query("SELECT COUNT(id) FROM movie WHERE visible = 'false'")
    suspend fun getCountNotVisible(): Int

    @Query("SELECT COUNT(id) FROM movie")
    suspend fun getCount(): Int
}