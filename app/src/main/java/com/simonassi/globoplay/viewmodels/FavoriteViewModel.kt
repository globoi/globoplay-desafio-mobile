package com.simonassi.globoplay.viewmodels

import androidx.lifecycle.*
import com.simonassi.globoplay.data.favorite.entity.Favorite
import com.simonassi.globoplay.data.favorite.FavoriteRepository
import javax.inject.Inject


class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository,
){

    val favoriteLiveData = MutableLiveData<List<Favorite>>()

}