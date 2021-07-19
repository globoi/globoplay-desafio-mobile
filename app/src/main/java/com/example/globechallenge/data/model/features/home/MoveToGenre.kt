package com.example.globechallenge.data.model.features.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieToGenre(val id: Int, val name: String, val listMovie: List<Movie>) : Parcelable {
    fun getMovies() = listMovie
}