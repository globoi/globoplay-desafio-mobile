package com.nroncari.movieplay.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.nroncari.movieplay.data.localdatasource.MovieLocalDataSource
import com.nroncari.movieplay.domain.model.MovieDataVideoDomain
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

class MovieLocalRepositoryImplTest {

    private val dataSource: MovieLocalDataSource = mock()

    private val repository by lazy {
        MovieLocalRepositoryImpl(dataSource)
    }

    private val dataVideoResponse = arrayListOf(
        MovieDataVideoDomain("/", ""),
        MovieDataVideoDomain("//", ""),
        MovieDataVideoDomain("///", "")
    )

    @Test
    fun `When get movie by id should return success`() = runBlockingTest {
        // Given
        whenever(dataSource.returnById(42)).thenReturn(42)

        // When
        val result = repository.returnById(42)

        // Then
        verify(dataSource).returnById(42)
        Assert.assertEquals(42, result!!.toInt())
        Assert.assertTrue(result is Long)
    }
}