package com.ftoniolo.globoplay.presentation.details

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DetailsFilmViewArg(
    val id: Long,
    val overview: String,
    val title: String,
    val imageUrl: String,
    val releaseDate: String,
):Parcelable
