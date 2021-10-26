package com.com.ifood.repository.room

import android.content.Context
import androidx.room.*
import com.com.ifood.repository.model.Movie
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

private lateinit var appDatabase: AppDatabase
private lateinit var movieDao: MovieDao

fun provideMovieLocalRepository(): Observable<MovieDao> = Observable
    .just(movieDao)
    .subscribeOn(Schedulers.io())

fun initDataBase(applicationContext: Context) {
    appDatabase = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "movies_ifood"
    ).build()

    movieDao = appDatabase.createMovieDao()
}

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun createMovieDao(): MovieDao
}


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie): Long

    @Query("DELETE FROM Movie WHERE id = :id")
    fun deleteById(id: Long)

    @Query("SELECT * FROM Movie ORDER BY id DESC")
    fun getMovies(): List<Movie>
}