package com.example.testeglobojeremias.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.testeglobojeremias.models.MovieEntity;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllMovies(ArrayList<MovieEntity> movies);

    @Query("DELETE FROM movie_table")
    void deleteAllMovies();

    @Query("SELECT * FROM movie_table")
    LiveData<List<MovieEntity>> getAllMovies();
}
