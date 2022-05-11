package com.ftoniolo.globoplay.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ftoniolo.core.data.DbConstants
import com.ftoniolo.core.domain.model.FilmFavorite

@Entity(tableName = DbConstants.FAVORITES_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = DbConstants.FAVORITES_TABLE_ID)
    val id: Long,
    @ColumnInfo(name = DbConstants.FAVORITES_TABLE_TITLE)
    val title: String,
    @ColumnInfo(name = DbConstants.FAVORITES_TABLE_IMAGE_URL)
    val imageUrl: String
)


fun List<FavoriteEntity>.toFavoriteFilmModel() =
    map {
        FilmFavorite(
            id = it.id, title = it.title, imageUrl = it.imageUrl
        )
    }
