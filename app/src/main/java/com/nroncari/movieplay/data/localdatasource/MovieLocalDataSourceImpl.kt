package com.nroncari.movieplay.data.localdatasource

import android.database.sqlite.SQLiteConstraintException
import com.nroncari.movieplay.data.localdatasource.dao.MovieDetailDAO
import com.nroncari.movieplay.data.model.MovieDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieLocalDataSourceImpl(
    private val dao: MovieDetailDAO
) : MovieLocalDataSource {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    override fun insert(movie: MovieDTO, inFailureCase: () -> Unit, inSuccessCase: () -> Unit) =
        scope.launch {
            tryRequisition(
                requisition = { dao.insert(movie) },
                inFailureCase = inFailureCase,
                inSuccessCase = inSuccessCase
            )
        }

    override fun delete(movie: MovieDTO, inFailureCase: () -> Unit, inSuccessCase: () -> Unit) =
        scope.launch {
            tryRequisition(
                requisition = { dao.delete(movie) },
                inFailureCase = inFailureCase,
                inSuccessCase = inSuccessCase
            )
        }

    override fun listAll() = dao.listAll()

    override suspend fun returnById(movieId: Long) = dao.returnById(movieId)

    private fun tryRequisition(
        requisition: () -> Unit,
        inFailureCase: () -> Unit,
        inSuccessCase: () -> Unit
    ) {
        var failure = false
        try {
            requisition()
        } catch (e: SQLiteConstraintException) {
            failure = true
            inFailureCase()
        } finally {
            if (!failure) {
                inSuccessCase()
            }
        }
    }
}