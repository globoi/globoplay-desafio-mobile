package com.example.globechallenge.data.model.models.details

import com.example.globechallenge.data.response.GenresItemDetail
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

data class MovieDetails(
    val title: String,
    val genres: List<GenresItemDetail>,
    val runtime: Int? = null,
    val overview: String? = null,
    val releaseDate: String? = null,    //"1988-10-21" -> "1988"
    val countryName: String? = null,
    val postPath: String
) {
    val year: String
        get() {
            val locale = Locale("en", "US")
            return try {
                val date = SimpleDateFormat("yyyy-MM-dd", locale).parse(releaseDate ?: "")
                SimpleDateFormat("yyyy", locale).format(date)
            }catch (e: ParseException) {
                ""
            }
        }
}