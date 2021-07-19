package com.example.globechallenge.data.repository.Favorities

import androidx.room.*
import com.example.globechallenge.data.model.entities.FavoriteMoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMoviesDao {

    //Flow is an coroutines interface that is reactive to work asynchronous flows
    @Query("SELECT * FROM favorite_movies_table ORDER BY name ASC")
    fun getAllFavoriteMovies(): Flow<List<FavoriteMoviesEntity>>

    //Using couroutines - suspend is similar to async in another languages
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteMoviesEntity: FavoriteMoviesEntity)

    //Using couroutines - suspend is similar to async in another languages
//    @Query("DELETE FROM favorite_movies_table WHERE id")
    @Query("DELETE FROM favorite_movies_table")
    suspend fun deleteOneFavoriteMovie()
//    @Delete
//    fun deleteOneFavoriteMovie(favoriteMoviesEntity : FavoriteMoviesEntity)
//
}