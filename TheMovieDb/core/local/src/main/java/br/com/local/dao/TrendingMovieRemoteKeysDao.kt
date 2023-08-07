package br.com.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import br.com.local.model.TrendingMovieRemoteKeyEntity

@Dao
interface TrendingMovieRemoteKeysDao {
    @Upsert
    suspend fun upsertAll(remoteKey: List<TrendingMovieRemoteKeyEntity>)

    @Query("SELECT * FROM trending_movie_remote_keys WHERE id = :movieId")
    suspend fun getRemoteKey(movieId: Int): TrendingMovieRemoteKeyEntity?

    @Query("DELETE FROM trending_movie_remote_keys")
    suspend fun clearAll()
}