package com.gmribas.globoplaydesafiomobile.core.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmribas.globoplaydesafiomobile.core.data.database.entity.MediaEntity

@Dao
interface MediaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMedia(media: MediaEntity): Long

    @Query("DELETE FROM media_entity WHERE id = :id")
    suspend fun removeMedia(id: Int): Int

    @Query("SELECT * FROM media_entity")
    suspend fun getAllMedia(): List<MediaEntity>
}