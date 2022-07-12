package com.nroncari.movieplay.domain.mapper

import com.nroncari.movieplay.domain.model.MovieListItemDomain
import com.nroncari.movieplay.presentation.model.MovieListItemPresentation
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MovieToPresentationMapperTest {

    private val mapper by lazy { MovieToPresentationMapper() }

    @Test
    fun `Give success when run map`() {
        // When
        val result = mapper.map(
            MovieListItemDomain(42, "Batman", "Homem Morcego", "//"),
        )

        // Then
        assertTrue(result is MovieListItemPresentation)
        assertEquals(42, result.id)
        assertEquals("Batman", result.originalTitle)
        assertEquals("Homem Morcego", result.title)
        assertEquals("//", result.posterPath)
    }
}