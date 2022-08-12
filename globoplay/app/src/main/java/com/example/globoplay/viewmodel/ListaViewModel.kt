package com.example.globoplay.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.globoplay.database.daos.MyListDao
import com.example.globoplay.database.models.MyList
import com.example.globoplay.database.repository.MediaRepository

class ListaViewModel(private val mediasFav:MediaRepository) : ViewModel() {

    val _mediaList: LiveData<List<MyList>> = mediasFav.medias

    val mediaList: LiveData<List<MyList>>
        get() = _mediaList

    suspend fun save(media: MyList){
        mediasFav.insert(media)
    }

    fun delete(media: String){
        mediasFav.delete(media)
    }
}