package br.com.local.model.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorities_movie_remote_keys")
data class FavoritiesMoviesRemoteKeyEntity (
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: Int,
        @ColumnInfo(name = "prevKey")
        val prevKey: Int?,
        @ColumnInfo(name = "nextKey")
        val nextKey: Int?,
     )