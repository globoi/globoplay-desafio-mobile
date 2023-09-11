package com.mazer.globoplayapp.domain.entities

import com.google.gson.annotations.SerializedName

data class Video(
    var id: String,
    var name: String,
    @SerializedName("key")
    var youtubeKey: String,
    var size: Int,
    @SerializedName("published_at")
    var publishDate: String,
    var type: String
)