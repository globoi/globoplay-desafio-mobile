package com.simonassi.globoplay.data.favorite

import com.simonassi.globoplay.data.favorite.dao.FavoriteDao
import com.simonassi.globoplay.data.favorite.entity.Favorite
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 *
 */
@Singleton
class FavoriteRepository @Inject constructor(private val favoriteDao: FavoriteDao) {

    fun getFavoriteList() = favoriteDao.getAll()

    suspend fun getFavoriteById(mediaId: Long) = favoriteDao.getAllByIds(mediaId)

    suspend fun delete(favorite: Favorite) = favoriteDao.delete(favorite)

    suspend fun deleteById(id: Long) = favoriteDao.deleteById(id)

    suspend fun saveFavorite(favorite: Favorite) = favoriteDao.insert(favorite)
}