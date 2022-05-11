package com.ftoniolo.core.data.repository

import com.ftoniolo.core.domain.model.FilmFavorite
import kotlinx.coroutines.flow.Flow

interface FavoritesLocalDataSource {

    fun getAll(): Flow<List<FilmFavorite>>

    suspend fun isFavorite(filmId: Long) : Boolean

    suspend fun save(filmFavorite: FilmFavorite)

    suspend fun delete(filmFavorite: FilmFavorite)

}