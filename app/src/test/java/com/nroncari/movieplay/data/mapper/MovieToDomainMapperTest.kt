package com.nroncari.movieplay.data.mapper

import com.nroncari.movieplay.data.model.MovieListItemResponse
import com.nroncari.movieplay.domain.model.MovieListItemDomain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MovieToDomainMapperTest {

    private val mapper by lazy { MovieToDomainMapper() }

    @Test
    fun `Give success when run map`() {
        // When
        val result = mapper.map(
            MovieListItemResponse(42, "Batman", "Homem Morcego", "//"),
        )

        // Then
        assertTrue(result is MovieListItemDomain)
        assertEquals(42, result.id)
        assertEquals("Batman", result.originalTitle)
        assertEquals("Homem Morcego", result.title)
        assertEquals("//", result.posterPath)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should error when run map with null title`() {
        // When
        mapper.map(MovieListItemResponse(42, "Batman", null, "//"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should error when run map with null originalTitle`() {
        // When
        mapper.map(MovieListItemResponse(42, null, "Homem Morcego", "//"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should error when run map with null posterPath`() {
        // When
        mapper.map(MovieListItemResponse(42, null, "Homem Morcego", null))
    }
}