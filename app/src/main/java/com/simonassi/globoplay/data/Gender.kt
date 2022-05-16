package com.simonassi.globoplay.data

import com.google.gson.annotations.SerializedName

data class Gender(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)