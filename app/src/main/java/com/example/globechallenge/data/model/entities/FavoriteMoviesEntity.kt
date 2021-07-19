package com.example.globechallenge.data.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.globechallenge.data.model.features.home.Movie

@Entity(tableName = "favorite_movies_table")
data class FavoriteMoviesEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val listMovie: String
)
