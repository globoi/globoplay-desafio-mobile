package com.nroncari.movieplay.data.localdatasource

import androidx.lifecycle.LiveData
import com.nroncari.movieplay.data.model.MovieDTO
import kotlinx.coroutines.Job

interface MovieLocalDataSource {

    fun insert(movie: MovieDTO, inFailureCase: () -> Unit, inSuccessCase: () -> Unit): Job

    fun delete(movie: MovieDTO, inFailureCase: () -> Unit, inSuccessCase: () -> Unit): Job

    fun listAll(): LiveData<List<MovieDTO>>

    suspend fun returnById(movieId: Long): Long?
}