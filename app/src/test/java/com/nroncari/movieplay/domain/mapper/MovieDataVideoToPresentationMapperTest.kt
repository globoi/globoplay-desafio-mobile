package com.nroncari.movieplay.domain.mapper

import com.nroncari.movieplay.domain.model.MovieDataVideoDomain
import com.nroncari.movieplay.presentation.model.MovieDataVideoPresentation
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MovieDataVideoToPresentationMapperTest {

    private val mapper by lazy {
        MovieDataVideoToPresentationMapper()
    }

    @Test
    fun `Give success when run map`() {
        // When
        val result = mapper.map(
            MovieDataVideoDomain("//", "moviedb.com")
        )

        // Then
        assertTrue(result is MovieDataVideoPresentation)
        assertEquals("//", result.path)
        assertEquals("moviedb.com", result.site)
    }
}