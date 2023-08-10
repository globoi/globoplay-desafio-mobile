package br.com.local.dao.movie_details

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import br.com.local.model.movie_details.MovieEntity
@Dao
interface MovieDetailsDao {
    @Upsert
    suspend fun upsert(entity: MovieEntity)

    @Query("DELETE FROM movies")
    suspend fun clearAll()

    @Query("SELECT * FROM movies WHERE id = :movieId")
    suspend fun getMovie(movieId: Int): MovieEntity?
}