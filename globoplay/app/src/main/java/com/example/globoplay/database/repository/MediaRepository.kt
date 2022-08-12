package com.example.globoplay.database.repository

import androidx.lifecycle.LiveData
import com.example.globoplay.database.daos.MyListDao
import com.example.globoplay.database.models.MyList

class MediaRepository(private val mediasDao: MyListDao){

    val medias: LiveData<List<MyList>>
        get()  = mediasDao.getAll()

    fun delete(media:String) = mediasDao.remove(media)

    suspend fun insert(media: MyList) = mediasDao.insert(media)
}