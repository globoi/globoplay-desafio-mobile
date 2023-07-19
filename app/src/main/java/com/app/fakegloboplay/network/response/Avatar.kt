package com.app.fakegloboplay.network.response

import com.google.gson.annotations.SerializedName


data class Avatar(

    @SerializedName("gravatar") var gravatar: Gravatar? = Gravatar(),
    @SerializedName("tmdb") var tmdb: Tmdb? = Tmdb()

)