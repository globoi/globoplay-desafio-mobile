package com.ftoniolo.globoplay.framework.local

import com.ftoniolo.core.data.repository.FavoritesLocalDataSource
import com.ftoniolo.core.domain.model.FilmFavorite
import com.ftoniolo.globoplay.framework.db.dao.FavoriteDao
import com.ftoniolo.globoplay.framework.db.entity.FavoriteEntity
import com.ftoniolo.globoplay.framework.db.entity.toFavoriteFilmModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomFavoritesDataSource @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoritesLocalDataSource {
    override fun getAll(): Flow<List<FilmFavorite>> {
        return favoriteDao.loadFavorites().map {
            it.toFavoriteFilmModel()
        }
    }

    override suspend fun isFavorite(filmId: Long): Boolean {
        return favoriteDao.hasFavorite(filmId) != null
    }

    override suspend fun save(filmFavorite: FilmFavorite) {
        favoriteDao.insertFavorite(filmFavorite.toFavoriteEntity())
    }

    override suspend fun delete(filmFavorite: FilmFavorite) {
        favoriteDao.deleteFavorite(filmFavorite.toFavoriteEntity())
    }

    private fun FilmFavorite.toFavoriteEntity() = FavoriteEntity(
        id, title, imageUrl
    )
}