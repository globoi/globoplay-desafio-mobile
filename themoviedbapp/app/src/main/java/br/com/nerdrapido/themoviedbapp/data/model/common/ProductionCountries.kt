package br.com.nerdrapido.themoviedbapp.data.model.common

import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
class ProductionCountries(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("name")
    val name: String
)