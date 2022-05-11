package com.ftoniolo.globoplay.framework.db.dao

import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Delete
import androidx.room.Dao
import com.ftoniolo.core.data.DbConstants
import com.ftoniolo.globoplay.framework.db.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM ${DbConstants.FAVORITES_TABLE_NAME}")
    fun loadFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM ${DbConstants.FAVORITES_TABLE_NAME} WHERE id = :filmId")
    suspend fun hasFavorite(filmId: Long) : FavoriteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Delete
    suspend fun  deleteFavorite(favoriteEntity: FavoriteEntity)
}