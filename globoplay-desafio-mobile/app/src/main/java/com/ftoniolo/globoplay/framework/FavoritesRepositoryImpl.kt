package com.ftoniolo.globoplay.framework

import com.ftoniolo.core.data.repository.FavoritesLocalDataSource
import com.ftoniolo.core.data.repository.FavoritesRepository
import com.ftoniolo.core.domain.model.FilmFavorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesLocalDataSource : FavoritesLocalDataSource
) : FavoritesRepository {
    override fun getAll(): Flow<List<FilmFavorite>> {
        return favoritesLocalDataSource.getAll()
    }

    override suspend fun isFavorite(filmId: Long): Boolean {
        return favoritesLocalDataSource.isFavorite(filmId)
    }

    override suspend fun saveFavorite(filmFavorite: FilmFavorite) {
        return favoritesLocalDataSource.save(filmFavorite)
    }

    override suspend fun deleteFavorite(filmFavorite: FilmFavorite) {
        return favoritesLocalDataSource.delete(filmFavorite)
    }
}