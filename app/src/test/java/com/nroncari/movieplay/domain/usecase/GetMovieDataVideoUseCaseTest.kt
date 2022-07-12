package com.nroncari.movieplay.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.nroncari.movieplay.domain.model.MovieDataVideoDomain
import com.nroncari.movieplay.domain.repository.MovieRemoteRepository
import com.nroncari.movieplay.presentation.model.MovieDataVideoPresentation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMovieDataVideoUseCaseTest {

    private val repository: MovieRemoteRepository = mock()

    private val useCase by lazy { GetMovieDataVideoUseCase(repository) }

    private val dataVideoDomain = arrayListOf(
        MovieDataVideoDomain("/", ""),
        MovieDataVideoDomain("//", "YouTube"),
        MovieDataVideoDomain("///", "")
    )

    private val dataVideoDomainNotSite = arrayListOf(
        MovieDataVideoDomain("/", ""),
        MovieDataVideoDomain("//", "Google"),
        MovieDataVideoDomain("///", "")
    )

    private val dataVideoDomainCamelCase = arrayListOf(
        MovieDataVideoDomain("/", ""),
        MovieDataVideoDomain("//", "youtUBE"),
        MovieDataVideoDomain("///", "")
    )

    @Test
    fun `When get movie data video from use case should return success`() = runBlockingTest {
        // Given
        whenever(repository.getMovieDataVideo(42)).thenReturn(dataVideoDomain)

        // When
        val result = useCase(42)

        // Then
        verify(repository).getMovieDataVideo(42)
        assertEquals("YouTube", result!!.site)
        assertTrue(result is MovieDataVideoPresentation)
    }

    @Test
    fun `When get movie data video not have youtube from use case should return success`() = runBlockingTest {
        // Given
        whenever(repository.getMovieDataVideo(42)).thenReturn(dataVideoDomainNotSite)

        // When
        val result = useCase(42)

        // Then
        verify(repository).getMovieDataVideo(42)
        assertTrue(result == null)
    }

    @Test
    fun `When get movie data video camelcase youtube from use case should return success`() = runBlockingTest {
        // Given
        whenever(repository.getMovieDataVideo(42)).thenReturn(dataVideoDomainCamelCase)

        // When
        val result = useCase(42)

        // Then
        verify(repository).getMovieDataVideo(42)
        assertEquals("youtUBE", result!!.site)
        assertTrue(result is MovieDataVideoPresentation)
    }
}