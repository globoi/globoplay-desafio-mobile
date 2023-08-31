package com.gmribas.globoplaydesafiomobile.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmribas.globoplaydesafiomobile.core.data.database.entity.MediaDetailsEntity

@Dao
interface MediaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMedia(media: MediaDetailsEntity): Long

    @Query("DELETE FROM media_details_entity WHERE id = :id")
    suspend fun removeMedia(id: Int): Int

    @Query("SELECT * FROM media_details_entity")
    suspend fun getAllMedia(): List<MediaDetailsEntity>

    @Query("SELECT * FROM media_details_entity WHERE id=:id")
    suspend fun findById(id: Int): MediaDetailsEntity?
}