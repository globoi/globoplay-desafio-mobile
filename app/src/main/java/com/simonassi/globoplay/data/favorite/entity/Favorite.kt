package com.simonassi.globoplay.data.favorite.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite (
    @PrimaryKey val tmdbId: Long,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "type") val type: Int?,
    @ColumnInfo(name = "cover") val cover: String?,
)