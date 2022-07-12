package com.nroncari.movieplay.data.mapper

import com.nroncari.movieplay.data.model.MovieDetailResponse
import com.nroncari.movieplay.domain.model.MovieDetailDomain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MovieDetailToDomainMapperTest {

    private val mapper by lazy { MovieDetailToDomainMapper() }

    @Test
    fun `Give success when run map`() {
        // When
        val result = mapper.map(
            MovieDetailResponse(
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

        // Then
        assertTrue(result is MovieDetailDomain)
        assertEquals(42, result.id)
        assertEquals("originalTitle", result.originalTitle)
        assertEquals("overview", result.overview)
        assertEquals("posterPath", result.posterPath)
        assertEquals("releaseDate", result.releaseDate)
        assertEquals("title", result.title)
        assertEquals("backdropPath", result.backdropPath)
        assertEquals(4.2.toFloat(), result.average)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should error when run map with null id`() {
        // When
        mapper.map(
            MovieDetailResponse(
                null,
                "originalTitle",
                "overview",
                "posterPath",
                "releaseDate",
                "title",
                "backdropPath",
                4.2.toFloat()
            )
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should error when run map with null originalTitle`() {
        // When
        mapper.map(
            MovieDetailResponse(
                42,
                null,
                "overview",
                "posterPath",
                "releaseDate",
                "title",
                "backdropPath",
                4.2.toFloat()
            )
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should error when run map with null overview`() {
        // When
        mapper.map(
            MovieDetailResponse(
                42,
                "originalTitle",
                null,
                "posterPath",
                "releaseDate",
                "title",
                "backdropPath",
                4.2.toFloat()
            )
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should error when run map with null posterPath`() {
        // When
        mapper.map(
            MovieDetailResponse(
                42,
                "originalTitle",
                "overview",
                null,
                "releaseDate",
                "title",
                "backdropPath",
                4.2.toFloat()
            )
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should error when run map with null releaseDate`() {
        // When
        mapper.map(
            MovieDetailResponse(
                42,
                "originalTitle",
                "overview",
                "posterPath",
                null,
                "title",
                "backdropPath",
                4.2.toFloat()
            )
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should error when run map with null title`() {
        // When
        mapper.map(
            MovieDetailResponse(
                42,
                "originalTitle",
                "overview",
                "posterPath",
                "releaseDate",
                null,
                "backdropPath",
                4.2.toFloat()
            )
        )
    }
}