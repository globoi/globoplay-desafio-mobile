package com.example.globechallenge

import com.example.globechallenge.data.model.models.details.MovieDetails
import com.example.globechallenge.data.response.GenresItemDetail
import org.junit.Assert
import org.junit.Test

class MovieDetailsTest {
    @Test
    fun `test should format date to year`() {
        val movieDetail = MovieDetails(
            "As panteras", listOf(GenresItemDetail("Comédia", 16)),
            90, "É um filme bastante engraçado.",
            "1988-10-21", "Brasil",
            "/ojDg0PGvs6R9xYFodRct2kdI6wC.jpg"
        )
        Assert.assertEquals("1988", movieDetail.year)
    }

    @Test
    fun `test should format date if null date`() {
        val movieDetail = MovieDetails(
            "As panteras", listOf(GenresItemDetail("Comédia", 16)),
            90, "É um filme bastante engraçado.", null,
            "Brasil", "/ojDg0PGvs6R9xYFodRct2kdI6wC.jpg"
        )
        Assert.assertEquals("", movieDetail.year)
    }

    @Test
    fun `test should format date if empty_date`() {
        val movieDetail = MovieDetails(
            "As panteras", listOf(GenresItemDetail("Comédia", 16)),
            90, "É um filme bastante engraçado.",
            "", "Brasil",
            "/ojDg0PGvs6R9xYFodRct2kdI6wC.jpg"
        )
        Assert.assertEquals("", movieDetail.year)
    }
}