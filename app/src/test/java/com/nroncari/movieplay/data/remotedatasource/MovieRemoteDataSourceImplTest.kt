package com.nroncari.movieplay.data.remotedatasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.nroncari.movieplay.data.model.*
import com.nroncari.movieplay.data.remotedatasource.Genre.ACTION
import com.nroncari.movieplay.data.remotedatasource.service.MovieRemoteService
import com.nroncari.movieplay.domain.model.MovieDataVideoDomain
import com.nroncari.movieplay.domain.model.MovieDetailDomain
import com.nroncari.movieplay.domain.model.MovieListItemDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRemoteDataSourceImplTest {

    private val service: MovieRemoteService = mock()

    private val dataSource by lazy {
        MovieRemoteDataSourceImpl(service)
    }

    private val movieResponse = arrayListOf(
        MovieListItemResponse(1, "Batman", "Homem Morcego", "//"),
        MovieListItemResponse(2, "SpiderMan", "Homem Aranha", "//"),
        MovieListItemResponse(3, "Kronos", "Tempo", "//"),
    )

    private val dataVideoResponse = arrayListOf(
        MovieDataVideoResponse("/", ""),
        MovieDataVideoResponse("//", ""),
        MovieDataVideoResponse("///", "")
    )

    @Test
    fun `When get movies by genre should return success`() = runBlockingTest {
        // Given
        whenever(service.getMoviesByGenre(page = 1, genre = ACTION)).thenReturn(
            BaseMovieListResponse(movieResponse)
        )

        // When
        val result = dataSource.getMoviesByGenre(1, ACTION)

        // Then
        verify(service).getMoviesByGenre(page = 1, genre = ACTION)
        assertEquals(3, result.size)
        assertTrue(result.first() is MovieListItemDomain)
    }

    @Test
    fun `When get movie detail by id should return success`() = runBlockingTest {
        // Given
        whenever(service.getMovieDetailBy(42)).thenReturn(
            MovieDetailResponse(42, "", "", "", "", "", "")
        )

        // When
        val result = dataSource.getMovieDetailBy(42)

        // Then
        verify(service).getMovieDetailBy(42)
        assertEquals(42, result.id)
        assertTrue(result is MovieDetailDomain)
    }

    @Test
    fun `When get recommendations movie by id should return success`() = runBlockingTest {
        // Given
        whenever(service.getMovieRecommendationsBy(42)).thenReturn(
            BaseMovieListResponse(movieResponse)
        )

        // When
        val result = dataSource.getMovieRecommendationsBy(42)

        // Then
        verify(service).getMovieRecommendationsBy(42)
        assertEquals(3, result.size)
        assertTrue(result.first() is MovieListItemDomain)
    }

    @Test
    fun `When get data video movie by id should return success`() = runBlockingTest {
        // Given
        whenever(service.getMovieDataVideo(42)).thenReturn(
            BaseMovieVideoListResponse(dataVideoResponse)
        )

        // When
        val result = dataSource.getMovieDataVideoSource(42)

        // Then
        verify(service).getMovieDataVideo(42)
        assertEquals(3, result.size)
        assertTrue(result.first() is MovieDataVideoDomain)
    }

    @Test
    fun `When get movies by keyword should return success`() = runBlockingTest {
        // Given
        whenever(service.getMoviesByKeyword("Batman")).thenReturn(
            BaseMovieListResponse(movieResponse)
        )

        // When
        val result = dataSource.getMoviesByKeyword("Batman")

        // Then
        verify(service).getMoviesByKeyword("Batman")
        assertEquals(3, result.size)
        assertTrue(result.first() is MovieListItemDomain)
    }
}