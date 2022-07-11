package com.nroncari.movieplay.data.localdatasource.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nroncari.movieplay.data.model.MovieDTO

@Dao
interface MovieDetailDAO {

    @Insert
    fun insert(movie: MovieDTO)

    @Delete
    fun delete(movie: MovieDTO)

    @Query("SELECT * FROM MovieDTO")
    fun listAll(): LiveData<List<MovieDTO>>

    @Query("SELECT movieDTO.id FROM MovieDTO WHERE movieDTO.id = :id LIMIT 1")
    suspend fun returnById(id: Long): Long?
}