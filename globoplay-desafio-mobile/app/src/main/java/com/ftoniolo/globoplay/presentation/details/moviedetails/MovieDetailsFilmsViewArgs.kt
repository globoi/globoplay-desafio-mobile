package com.ftoniolo.globoplay.presentation.details.moviedetails

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class MovieDetailsFilmsViewArgs(
    val overview: String,
    val title: String,
    val releaseDate: String
): Parcelable

