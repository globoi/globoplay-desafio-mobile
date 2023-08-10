package br.com.local.dao.favorities

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import br.com.local.model.favorite.FavoritiesMoviesRemoteKeyEntity

@Dao
interface FavoritiesMovieRemoteKeysDao {
    @Upsert
    suspend fun upsertAll(remoteKey: List<FavoritiesMoviesRemoteKeyEntity>)

    @Query("SELECT * FROM favorities_movie_remote_keys WHERE id = :movieId")
    suspend fun getRemoteKey(movieId: Int): FavoritiesMoviesRemoteKeyEntity?

    @Query("DELETE FROM favorities_movie_remote_keys")
    suspend fun clearAll()
}