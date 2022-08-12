package com.example.globoplay.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.globoplay.database.models.MyList
import com.example.globoplay.domain.PopularMovie

@Dao
interface MyListDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(media:MyList)


    @Query("SELECT * FROM MyList")
    fun getAll(): LiveData<List<MyList>>

    @Query("DELETE FROM MyList WHERE mediaName = :media")
    fun remove(media: String?)
}