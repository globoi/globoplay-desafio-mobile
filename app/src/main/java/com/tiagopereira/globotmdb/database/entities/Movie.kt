package com.tiagopereira.globotmdb.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = false) @ColumnInfo("id") val id: Int,
    @ColumnInfo("adult") val adult: Boolean,
    @ColumnInfo("backdrop_path") val backdropPath: String,
    @ColumnInfo("budget") val budget: Int,
    @ColumnInfo("homepage") val homepage: String,
    @ColumnInfo("imdb_id") val imdbId: String,
    @ColumnInfo("original_language") val originalLanguage: String,
    @ColumnInfo("original_title") val originalTitle: String,
    @ColumnInfo("overview") val overview: String,
    @ColumnInfo("popularity") val popularity: Double,
    @ColumnInfo("poster_path") val posterPath: String,
    @ColumnInfo("release_date") val releaseDate: String,
    @ColumnInfo("revenue") val revenue: Int,
    @ColumnInfo("runtime") val runtime: Int,
    @ColumnInfo("status") val status: String,
    @ColumnInfo("tagline") val tagline: String,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("video") val video: Boolean,
    @ColumnInfo("vote_average") val voteAverage: Double,
    @ColumnInfo("vote_count") val voteCount: Int,
    @ColumnInfo("visible") val visible: Boolean = false
)