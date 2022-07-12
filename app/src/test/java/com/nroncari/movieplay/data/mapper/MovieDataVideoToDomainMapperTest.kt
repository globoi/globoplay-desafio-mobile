package com.nroncari.movieplay.data.mapper

import com.nroncari.movieplay.data.model.MovieDataVideoResponse
import com.nroncari.movieplay.domain.model.MovieDataVideoDomain
import org.junit.Assert.*
import org.junit.Test

class MovieDataVideoToDomainMapperTest {

    private val mapper by lazy {
        MovieDataVideoToDomainMapper()
    }

    @Test
    fun `Give success when run map`() {
        // When
        val result = mapper.map(
            MovieDataVideoResponse("//", "moviedb.com")
        )

        // Then
        assertTrue(result is MovieDataVideoDomain)
        assertEquals("//", result.path)
        assertEquals("moviedb.com", result.site)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should error when run map with null path`() {
        // When
        mapper.map(MovieDataVideoResponse(null, "moviedb.com"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should error when run map with null site`() {
        // When
        mapper.map(MovieDataVideoResponse("//", null))
    }
}