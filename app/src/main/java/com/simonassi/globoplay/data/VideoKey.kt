package com.simonassi.globoplay.data

import com.google.gson.annotations.SerializedName

data class VideoKey(
    @field:SerializedName("key") val key: String,
    @field:SerializedName("site") val site: String,
)