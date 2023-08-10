package br.com.local.model.trending

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.local.model.MovieEntity

@Entity(tableName = "trending_movies")
data class TrendingMovieEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,
    @Embedded
    val movie: MovieEntity,

    @ColumnInfo(name = "order")
    val order: Int = 0
)