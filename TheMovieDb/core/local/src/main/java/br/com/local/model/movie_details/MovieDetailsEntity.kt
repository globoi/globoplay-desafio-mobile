package br.com.local.model.movie_details

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieDetailsEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "adult")
    val adult: Boolean?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "tagline")
    val tagline: String?,
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,
    @ColumnInfo(name = "original_title")
    val originalTitle : String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "popularity")
    val popularity : Double? ,
    @ColumnInfo(name = "video")
    val video : Boolean?,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,
    @ColumnInfo(name = "vote_count")
    val voteCount : Int?,
    @ColumnInfo(name = "genre")
    val genre: List<GenreEntity>,
)