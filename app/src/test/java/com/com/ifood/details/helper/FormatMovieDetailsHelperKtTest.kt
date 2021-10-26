package com.com.ifood.details.helper

import com.com.ifood.details.model.MovieDetails
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FormatMovieDetailsHelperKtTest {

    @Test
    fun whenListIsNullOrEmpty_ShouldReturnNull() {
        assertNull(getNamesFormattedMovieDetails(null))
        assertNull(getNamesFormattedMovieDetails(mutableListOf()))
    }

    @Test
    fun whenListHasOneName_ShouldReturnJustThisName() {
        val name = "Ficcao"
        val movieDetails = mutableListOf(
            MovieDetails(name)
        )

        assertEquals(name, getNamesFormattedMovieDetails(movieDetails))
    }

    @Test
    fun whenListHasMoreThanOneName_ShouldReturnNameFormatted() {
        val name1 = "Ficcao"
        val name2 = "Comedia"

        val movieDetails = mutableListOf(
            MovieDetails(name1), MovieDetails(name2)
        )

        assertEquals("$name1, $name2", getNamesFormattedMovieDetails(movieDetails))
    }

}
