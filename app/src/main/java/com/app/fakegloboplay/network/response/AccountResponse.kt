package com.app.fakegloboplay.network.response

import com.google.gson.annotations.SerializedName


data class AccountResponse(

    @SerializedName("avatar") var avatar: Avatar? = Avatar(),
    @SerializedName("id") var id: Int? = null,
    @SerializedName("iso_639_1") var iso6391: String? = null,
    @SerializedName("iso_3166_1") var iso31661: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("include_adult") var includeAdult: Boolean? = null,
    @SerializedName("username") var username: String? = null

)