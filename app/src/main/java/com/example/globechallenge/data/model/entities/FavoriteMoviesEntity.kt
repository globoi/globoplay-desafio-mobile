package com.example.globechallenge.data.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies_table")
data class FavoriteMoviesEntity (
    @ColumnInfo val image: String,
    @PrimaryKey val id: String
)