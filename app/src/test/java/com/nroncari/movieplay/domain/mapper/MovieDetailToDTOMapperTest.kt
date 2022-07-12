package com.nroncari.movieplay.domain.mapper

import com.nroncari.movieplay.data.model.MovieDTO
import com.nroncari.movieplay.presentation.model.MovieDetailPresentation
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MovieDetailToDTOMapperTest {

    private val mapper by lazy {
        MovieDetailToDTOMapper()
    }

    @Test
    fun `Give success when run map`() {
        // When
        val result = mapper.map(
            MovieDetailPresentation(
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
        assertTrue(result is MovieDTO)
        assertEquals(42, result.id)
        assertEquals("originalTitle", result.originalTitle)
        assertEquals("overview", result.overview)
        assertEquals("posterPath", result.posterPath)
        assertEquals("releaseDate", result.releaseDate)
        assertEquals("title", result.title)
        assertEquals("backdropPath", result.backdropPath)
        assertEquals(4.2.toFloat(), result.average)
    }
}