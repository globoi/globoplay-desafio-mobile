package com.nroncari.movieplay.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.nroncari.movieplay.data.localdatasource.MovieLocalDataSource
import com.nroncari.movieplay.data.remotedatasource.MoviePagingSourceByGenre
import com.nroncari.movieplay.data.remotedatasource.MoviePagingSourceByKeyword
import com.nroncari.movieplay.data.remotedatasource.MoviePagingSourceRecommendations
import com.nroncari.movieplay.data.remotedatasource.MovieRemoteDataSource
import com.nroncari.movieplay.domain.model.MovieDataVideoDomain
import com.nroncari.movieplay.domain.model.MovieDetailDomain
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

class MovieRemoteRepositoryImplTest {

    private val remoteDataSource: MovieRemoteDataSource = mock()
    private val moviePagingSourceByGenre: MoviePagingSourceByGenre = mock()
    private val moviePagingSourceRecommendations: MoviePagingSourceRecommendations = mock()
    private val moviePagingSourceByKeyword: MoviePagingSourceByKeyword = mock()

    private val repository by lazy {
        MovieRemoteRepositoryImpl(
            remoteDataSource,
            moviePagingSourceByGenre,
            moviePagingSourceRecommendations,
            moviePagingSourceByKeyword
        )
    }

    private val dataVideoResponse = arrayListOf(
        MovieDataVideoDomain("/", ""),
        MovieDataVideoDomain("//", ""),
        MovieDataVideoDomain("///", "")
    )

    @Test
    fun `When get movie detail by id should return success`() = runBlockingTest {
        // Given
        whenever(remoteDataSource.getMovieDetailBy(42)).thenReturn(
            MovieDetailDomain(42, "", "", "", "", "", "")
        )

        // When
        val result = repository.getMovieDetailBy(42)

        // Then
        verify(remoteDataSource).getMovieDetailBy(42)
        Assert.assertEquals(42, result.id)
        Assert.assertTrue(result is MovieDetailDomain)
    }

    @Test
    fun `When get data video movie by id should return success`() = runBlockingTest {
        // Given
        whenever(remoteDataSource.getMovieDataVideoSource(42)).thenReturn(dataVideoResponse)

        // When
        val result = repository.getMovieDataVideo(42)

        // Then
        verify(remoteDataSource).getMovieDataVideoSource(42)
        Assert.assertEquals(3, result.size)
        Assert.assertTrue(result.first() is MovieDataVideoDomain)
    }
}