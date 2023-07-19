package com.app.fakegloboplay.network.response

import com.google.gson.annotations.SerializedName


data class Credits(

    @SerializedName("cast") var cast: ArrayList<Cast> = arrayListOf(),
    @SerializedName("crew") var crew: ArrayList<Crew> = arrayListOf(),
    @SerializedName("id") var id: Int? = null

)