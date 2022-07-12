package com.nroncari.movieplay.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.nroncari.movieplay.domain.repository.MovieLocalRepository
import com.nroncari.movieplay.domain.repository.MovieRemoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMovieDatabaseByIdTest {

    private val repository: MovieLocalRepository = mock()

    private val useCase by lazy { GetMovieDatabaseById(repository) }

    @Test
    fun `When get movie By Id from use case should return success`() = runBlockingTest {
        // Given
        whenever(repository.returnById(42)).thenReturn(42)

        // When
        val result = useCase(42)

        // Then
        verify(repository).returnById(42)
        assertEquals(42, result!!.toLong())
        assertTrue(result is Long)
    }
}