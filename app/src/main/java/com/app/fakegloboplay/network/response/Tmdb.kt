package com.app.fakegloboplay.network.response

import com.google.gson.annotations.SerializedName


data class Tmdb(

    @SerializedName("avatar_path") var avatarPath: String? = null

)