package com.app.fakegloboplay.network.response

import com.google.gson.annotations.SerializedName


data class Gravatar(

    @SerializedName("hash") var hash: String? = null

)