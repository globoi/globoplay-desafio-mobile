package com.ftoniolo.globoplay.presentation.details

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class TabViewPagerArgs(
    val id: Long,
    val overview: String,
    val title: String,
    val releaseDate: String
): Parcelable

