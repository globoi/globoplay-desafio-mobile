package com.simonassi.globoplay.data.favorite.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.simonassi.globoplay.data.favorite.entity.Favorite

/**
 * The Data Access Object for the Favorite class.
 */
@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    fun getAll(): LiveData<List<Favorite>>

    @Query("SELECT * FROM favorites WHERE tmdbId = :mediaId")
    suspend fun getAllByIds(mediaId: Long): List<Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg favorite: Favorite)

    @Delete
    suspend fun delete(favorite: Favorite)

    @Query("DELETE FROM favorites WHERE tmdbId = :id")
    suspend fun deleteById(id: Long)


}