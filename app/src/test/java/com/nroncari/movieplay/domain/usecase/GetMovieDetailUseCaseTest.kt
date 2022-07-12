package com.nroncari.movieplay.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.nroncari.movieplay.domain.model.MovieDetailDomain
import com.nroncari.movieplay.domain.repository.MovieRemoteRepository
import com.nroncari.movieplay.presentation.model.MovieDetailPresentation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMovieDetailUseCaseTest {

    private val repository: MovieRemoteRepository = mock()

    private val useCase by lazy { GetMovieDetailUseCase(repository) }

    @Test
    fun `When get movie detail by Id from use case should return success`() = runBlockingTest {
        // Given
        whenever(repository.getMovieDetailBy(42)).thenReturn(
            MovieDetailDomain(
                42,
                "originalTitle",
                "overview",
                "posterPath",
                "releaseDate",
                "title",
                "backdropPath",
                4.2.toFloat()
            )
        )

        // When
        val result = useCase(42)

        // Then
        verify(repository).getMovieDetailBy(42)
        assertEquals(42, result.id)
        assertTrue(result is MovieDetailPresentation)
    }
}