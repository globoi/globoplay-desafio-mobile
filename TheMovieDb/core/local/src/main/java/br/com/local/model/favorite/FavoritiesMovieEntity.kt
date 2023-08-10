package br.com.local.model.favorite

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.local.model.MovieEntity

@Entity(tableName = "favorities_movies")
data class FavoritiesMovieEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,
    @Embedded
    val movie: MovieEntity,
    @ColumnInfo(name = "order")
    val order: Int = 0
)