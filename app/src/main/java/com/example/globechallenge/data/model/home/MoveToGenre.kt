package com.example.globechallenge.data.model

import android.os.Parcelable
import com.example.globechallenge.data.model.home.Movie
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieToGenre(val id: Int, val name: String, val listMovie: List<Movie>) : Parcelable {
    fun getMovies() = listMovie
}