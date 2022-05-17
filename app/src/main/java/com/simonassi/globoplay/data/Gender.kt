package com.simonassi.globoplay.data

import com.google.gson.annotations.SerializedName

data class Gender(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
)