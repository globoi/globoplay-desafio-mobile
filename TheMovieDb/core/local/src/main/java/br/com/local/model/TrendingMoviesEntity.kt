package br.com.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trending_movies")
data class TrendingMovieEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("adult")
    val adult: Boolean?,
    @ColumnInfo("backdrop_path")
    val backdropPath: String?,
    @ColumnInfo("first_air_date")
    val firstAirDate: String?,
    @ColumnInfo("genre_ids")
    val genreIds: List<Int>? = null,
    @ColumnInfo("media_type")
    val mediaType: String?,
    @ColumnInfo("name")
    val name: String?,
    @ColumnInfo("origin_country")
    val originCountry: List<String>? = null,
    @ColumnInfo("original_language")
    val originalLanguage: String?,
    @ColumnInfo("original_name")
    val originalName: String?,
    @ColumnInfo("original_title")
    val originalTitle: String?,
    @ColumnInfo("overview")
    val overview: String?,
    @ColumnInfo("popularity")
    val popularity: Double?,
    @ColumnInfo("poster_path")
    val posterPath: String?,
    @ColumnInfo("release_date")
    val releaseDate: String?,
    @ColumnInfo("title")
    val title: String?,
    @ColumnInfo("video")
    val video: Boolean?,
    @ColumnInfo("vote_average")
    val voteAverage: Double?,
    @ColumnInfo("vote_count")
    val voteCount: Int?,
    @ColumnInfo(name = "order")
    val order: Int = 0
)