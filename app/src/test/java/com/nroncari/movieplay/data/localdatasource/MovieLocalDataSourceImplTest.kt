package com.nroncari.movieplay.data.localdatasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.nroncari.movieplay.data.localdatasource.dao.MovieDetailDAO
import com.nroncari.movieplay.data.model.BaseMovieListResponse
import com.nroncari.movieplay.data.remotedatasource.Genre
import com.nroncari.movieplay.domain.model.MovieListItemDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieLocalDataSourceImplTest {

    private val dao: MovieDetailDAO = mock()

    private val dataSource by lazy {
        MovieLocalDataSourceImpl(dao)
    }

    @Test
    fun `When get movie by id should return success`() = runBlockingTest {
        // Given
        whenever(dao.returnById(42)).thenReturn(42)

        // When
        val result = dataSource.returnById(42)

        // Then
        verify(dao).returnById(42)
        assertEquals(42, result!!.toInt())
        assertTrue(result is Long)
    }
}