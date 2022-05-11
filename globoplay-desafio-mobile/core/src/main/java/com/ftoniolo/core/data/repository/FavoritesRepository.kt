package com.ftoniolo.core.data.repository

import com.ftoniolo.core.domain.model.FilmFavorite
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun getAll(): Flow<List<FilmFavorite>>

    suspend fun isFavorite(filmId: Long) : Boolean

    suspend fun saveFavorite(filmFavorite: FilmFavorite)

    suspend fun deleteFavorite(filmFavorite: FilmFavorite)
}