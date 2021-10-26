package com.com.ifood.details.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetails(
    @SerializedName("name")
    @Expose(serialize = false)
    val name: String
) : Parcelable